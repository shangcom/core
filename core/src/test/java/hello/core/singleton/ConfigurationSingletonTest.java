package hello.core.singleton;

import hello.core.AppConfig;
import hello.core.member.MemberRepository;
import hello.core.member.MemberServiceImpl;
import hello.core.order.OrderServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;

public class ConfigurationSingletonTest {

    @Test
    void configurationTest() {
        // Arrange
        // TODO: Initialize test data
        // @Configuration을 바탕으로 ApplicationContext(스프링 컨테이너)가 생성되면, Config 클래스의 @Bean을 바탕오르
        // 빈 메타 정보와 빈 객체가 스프링 컨테이너에 등록됨.
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

        MemberServiceImpl memberService = ac.getBean("memberService", MemberServiceImpl.class);
        OrderServiceImpl orderService = ac.getBean("orderService", OrderServiceImpl.class);
        MemberRepository memberRepository = ac.getBean("memberRepository", MemberRepository.class);

        // Act
        // TODO: Call the method to be tested
        MemberRepository memberRepositoryMember = memberService.getMemberRepository();
        MemberRepository memberRepositoryOrder = orderService.getMemberRepository();

        System.out.println("memberRepository1 = " + memberRepositoryMember);
        System.out.println("memberRepository2 = " + memberRepositoryOrder);
        System.out.println("memberRepository2 = " + memberRepository);

        // Assert
        // TODO: Verify the results
        assertThat(memberRepositoryMember).isSameAs(memberRepositoryOrder);

    }

    @Test
    @DisplayName("@Configuration 붙은 클래스는 프록시 클래스로 변경되어 빈으로 등록된다.")
    void configurationDeep() {
        // Arrange
        // TODO: Initialize test data
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
        AppConfig bean = ac.getBean(AppConfig.class);

        // Act
        // TODO: Call the method to be tested
        System.out.println("bean.getClass() = " + bean.getClass());
        // Assert
        // TODO: Verify the results

        assertThat(bean).isInstanceOf(AppConfig.class);
    }
}
