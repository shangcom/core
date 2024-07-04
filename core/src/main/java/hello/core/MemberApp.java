package hello.core;

import hello.core.config.AppConfig;
import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MemberApp {
    public static void main(String[] args) {

//        AppConfig appConfig = new AppConfig();
//        MemberService memberService = appConfig.memberService();

        /**
         * ApplicationContext: 스프링 컨테이너 인터페이스. 빈을 관리하는 역할.
         * 스프링 컨테이너에 환경정보를 입력해주고, 이 컨테이너를 통해 생성된 빈을 받아온다.
         * xml 파일 기반 / java 설정 클래스 기반에 따라 구현체가 달라짐.
         * 현재는 AppConfig 클래스를 기반으로하니까 AnnotationConfigApplicationContext를 사용했음.
         * xml 파일 기반이었다면 ClassPathXmlApplicationContext 사용할 수 있음.
         */
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
/**
 * getBean(String name, Class<T> requiredType)
 * 매개변수 : 스프링 컨테이너에 등록된 빈 이름, 가져오려는 빈의 클래스 타입.
 * 두 매개변수 중 하나만 사용할 수 있음.
 * 빈의 클래스 타입만을 매개변수로 사용했을 경우, 같은 타입의 빈이 여러개 있으면 예외 발생.
 */
        MemberService memberService = applicationContext.getBean("memberService", MemberService.class);

        Member member = new Member(1L, "memberA", Grade.VIP);
        memberService.join(member);

        Member findMember = memberService.findMember(member.getId());

        System.out.println("new member = " + member.getName());
        System.out.println("find Member = " + findMember.getName());
    }
}
