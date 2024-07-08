package hello.core.config;

import hello.core.discountPolicy.DiscountPolicy;
import hello.core.discountPolicy.RateDiscountPolicy;
import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/*
* @Configuration : 스프링 설정 정보를 저장하는 클래스.
* 스프링 컨테이너가 생성될 때 필요한 정보를 제공한다.
*/
@Configuration
public class AppConfig {

    /*
     * 여기 쓰여진 정보를 바탕으로 스프링 컨테이너 구현체 안의 스프링 빈 저장소에 '빈 이름 + 빈 객체'가 저장된다.
     * 빈 사이의 의존관계 역시 여기 쓰여진 정보를 바탕으로 함께 처리된다.
     * 결과적으로, AppConfig 클래스는 스프링 컨테이너가 생성될 때 한 번만 사용되며,
     * 이를 통해 등록된 싱글톤 빈 객체의 참조를 가져다 쓰는거다.
     * 스프링 컨테이너에서 빈 객체를 반환할 때, name 매개변수는 @Bean 메서드의 이름과 같음
     * 따라서 @Bean을 작성할 때는 메서드가 반환하는 클래스와 메서드 이름을 일치시키는 것이 관례임.
     * 만약 다른 이름으로 하고 싶다면, @Bean(name="원하는 이름")으로 이름 지정해주면 된다.
     * MemberService memberService = applicationContext.getBean("원하는 이름", MemberService.class);
     * bean 태그의 name 속성 이용하면 이렇게 할 수 있음.
     * name 속성 사용하는 이유 :
     * 빈 이름 충돌 방지: 동일한 타입의 여러 빈을 정의할 때 name 속성을 사용하여 빈 이름을 명시적으로 지정하여 충돌을 방지.
     * 명확한 빈 식별: 여러 구현체를 사용할 때 각 빈을 명확하게 식별.
     * 외부 라이브러리와의 충돌 방지: 외부 라이브러리나 프레임워크에서 제공하는 빈과 이름이 충돌하지 않도록 사용자 정의 빈 이름을 명시적으로 지정.
     */
    @Bean
    public MemberService memberService() {
        System.out.println("call AppConfig.memberService");
        return new MemberServiceImpl(memberRepository());
    }

    @Bean
    public OrderService orderService() {
        System.out.println("call AppConfig.orderService");
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }

    @Bean
    public MemberRepository memberRepository() {
        System.out.println("call AppConfig.memberRepository");
        return new MemoryMemberRepository();
    }

    @Bean
    public DiscountPolicy discountPolicy() {
//        return new FixDiscountPolicy();
        return new RateDiscountPolicy();
    }

}
