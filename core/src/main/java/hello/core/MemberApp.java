package hello.core;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MemberApp {
    public static void main(String[] args) {

//        AppConfig appConfig = new AppConfig();
//        MemberService memberService = appConfig.memberService();

        /*
         * ApplicationContext: 스프링 컨테이너 인터페이스. 빈을 관리하는 역할.
         * 이 인터페이스를 통해 스프링 컨테이너를 생성할 때 @Configuration에 있는 환경정보를 넘겨줘야 한다.
         * @Configuration에 있는 환경정보를 바탕으로, 스프링 컨테이너 안의 스프링 빈 저장소에
         * 빈 이름 + 빈 객체(싱글톤 패턴) 조합으로 저장된다.
         * 빈 이름은 @Bean 메서드의 이름과 동일하다. name 속성을 사용해서 다른 이름으로 지정해줄 수 있다.
         * getBean 메서드를 통해 스프링 컨테이너의 스프링 빈 저장소에 존재하는 싱글톤 객체의 참조(주소)를 받아서 사용하는 것이다.
         * xml 파일 기반 / java 설정 클래스 기반에 따라 구현 클래스가 달라짐.
         * 현재는 AppConfig 클래스를 기반으로하니까 AnnotationConfigApplicationContext를 사용했음.
         * xml 파일 기반이었다면 ClassPathXmlApplicationContext 사용할 수 있음.
         */
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);

/*
 * getBean(String name, Class<T> requiredType)
 * 매개변수 : 스프링 컨테이너에 등록된 빈 이름, 가져오려는 빈의 클래스 타입.
 * 스프링 컨테이너에 등록될 때는 메서드 이름으로 등록됨.
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
