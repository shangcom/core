package hello.core.singleton;

import hello.core.config.AppConfig;
import hello.core.member.MemberRepository;
import hello.core.member.MemberServiceImpl;
import hello.core.order.OrderServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;

public class ConfigurationSingletonTest {

    @Test
    void configurationTest() {
        // Arrange
        // TODO: Initialize test data
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
}
