package hello.core.singleton;

import hello.core.AppConfig;
import hello.core.member.MemberService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;

public class SingletonTest {

    AppConfig ac = new AppConfig();
//new SingletonService(); // SingletonService의 생성자를 private로 막아뒀기 때문에 해당 클래스 밖에서는 생성자 호출 불가능.

    /**
     * 스프링 컨테이너를 사용할 때와 달리 싱글톤 패턴이 적용되지 않기 때문에, 매 요청마다 새로운 객체를 생성한다.
     */
    @Test
    @DisplayName("스프링 없는 순수한 DI 컨테이너")
    void pureContainer() {
        // Arrange
        // TODO: Initialize test data
        MemberService memberService1 = ac.memberService();
        MemberService memberService2 = ac.memberService();

        // Act
        // TODO: Call the method to be tested

        // Assert
        // TODO: Verify the results
        assertThat(memberService1).isNotSameAs(memberService2);
    }

    @Test
    @DisplayName("싱글톤 패턴을 적용한 객체 사용")
    void singletonServiceTEst() {
        // Arrange
        // TODO: Initialize test data
        SingletonService singletonService1 = SingletonService.getInstance();
        SingletonService singletonService2 = SingletonService.getInstance();

        // Act
        // TODO: Call the method to be tested

        // Assert
        // TODO: Verify the results
        assertThat(singletonService1).isSameAs(singletonService2);
    }

    @Test
    @DisplayName("스프링 컨테이너와 싱글톤")
    void springContainer() {
        // Arrange
        // TODO: Initialize test data
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
        MemberService memberService1 = ac.getBean("memberService", MemberService.class);
        MemberService memberService2 = ac.getBean("memberService", MemberService.class);
        // Act
        // TODO: Call the method to be tested

        // Assert
        // TODO: Verify the results
        assertThat(memberService1).isSameAs(memberService2);
    }
}
