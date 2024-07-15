package hello.core.common;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import java.util.UUID;

/*
 * 두 어노테이션 함께 사용하면 싱글톤 객체가 생성되는 것이 아니라, HTTP 요청이 발생할 때마다 새로운 인스턴스 반환함.
 * @Prototype 스코프와 달리, Request 스코프는 HTTP 요청이 완료될 때까지 스프링에서 빈을 관리하므로 @PreDestroy 메서드가 작동한다.
 *
 * request 스코프 빈은 반드시 지연 주입을 해야하며, Provider와 함께 사용해야 한다.
 * request 스코프 빈은 HTTP 요청 컨텍스트가 있을 때 생성된다.
 * 애플리케이션 구동하면(스프링 컨텍스트가 초기화될 때) 싱글톤 빈이 생성된다.
 * 이때, 싱글톤 빈들 중 request scope의 bean을 주입받아야 하는 것들이 있다.
 * 그러나 리퀘스트 스코프 빈은 HTTP request 컨텍스트가 있어야지만 초기화된다.
 * 앱이 막 구동한 상태이므로, HTTP request는 존재하지 않는다.
 * 따라서 주입되어야할 빈이 생성되지 못하고, 구동에 실패한다.
 * 이를 방지하기 위해, request scope의 컴포넌트는 반드시 Provider를 이용해 Lazy Injection(지연 주입) 해야만 한다.
 * MyLogger 의존성을 가진 클래스들은 MyLogger을 바로 주입하는 것이 아니라,
 * Provider<주입 받을 클래스> provider; 에 생성자를 통해 Provider를 빈으로 등록하여 주입받고,
 * 이 provider를 이용해 request 스코프의 빈을 생성하여 사용하면 된다.
 * */

/**
 * Scope proxyMode 속성
 * 프록시 생성: 스프링 컨테이너는 요청 스코프 빈을 상속하는 프록시 객체를 생성.
 * 싱글톤 빈이 생성될 때 request 스코프의 의존성 객체 대신(아직 생성할 수 없음) 프록시 빈을 주입함.
 * 프록시 역할: HTTP 요청이 발생할 때마다 프록시 빈은 실제 요청 스코프 빈을 생성함.
 * 생명주기 관리: 프록시가 반환하는 실제 빈은 HTTP 요청이 끝날 때까지 유지되며, 요청이 종료되면 소멸됨.
 */
@Component
@Scope(value = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class MyLogger {

    private String uuid;
    private String requestURL;

    public void setRequestURL(String requestURL) {
        this.requestURL = requestURL;
    }

    public void log(String message) {

        System.out.println("[" + uuid + "]" + "[" + requestURL + "] " + message);
    }

    @PostConstruct
    public void init() {
        this.uuid = UUID.randomUUID().toString();
        System.out.println("[" + uuid + "] request scope bean created:" + this);
    }

    @PreDestroy
    public void close() {
        System.out.println("[" + uuid + "] request scope bean closed:" + this);
    }
}
