package hello.core.scope;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

public class PrototypeTest {

    @Test
    void prototypeBeanFind() {
        // Arrange
        // TODO: Initialize test data
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(PrototypeBean.class);

        // Act
        // TODO: Call the method to be tested
        System.out.println("find prototypeBean1");
        PrototypeBean prototypeBean1 = ac.getBean(PrototypeBean.class);

        System.out.println("find prototypeBean2");
        PrototypeBean prototypeBean2 = ac.getBean(PrototypeBean.class);

        System.out.println("prototypeBean1 = " + prototypeBean1);
        System.out.println("prototypeBean2 = " + prototypeBean2);

        // Assert
        // TODO: Verify the results : ac.close() 동작하지 않는다. @Scope("prototype")은 생성된 이후로는 스프링 관리 받을 수 없음.
        // 따라서 destroy 메서드 수동으로 실행해줘야만 한다.
        Assertions.assertThat(prototypeBean1).isNotSameAs(prototypeBean2);
        ac.close(); // 작동 안함.
        prototypeBean1.destroy();
        prototypeBean2.destroy();
    }

    /**
     * 프로토타입 스코프 : 스프링 컨테이너에 요청할 때마다 새로 생성.
     * 스프링 컨테이너는 프로토타입 빈의 생성과 의존관계 주입, 초기화까지만 관여.
     * 종료 메서드 호출되지 않음.
     * 프로토타입 빈은 프로토타입 빈을 조회한 클라이언트가 해야한다. 종료 메서드 호출도 클라이언트가 직접 해야한다.
     */
    @Scope("prototype")
    static class PrototypeBean {

        @PostConstruct
        public void init() {
            System.out.println("PrototypeBean.init");
        }

        @PreDestroy
        public void destroy() {
            System.out.println("PrototypeBean.destroy");
        }
    }
}
