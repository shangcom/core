package hello.core.scan;

import hello.core.AppConfig;
import hello.core.AutoAppConfig;
import hello.core.member.MemberService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

public class AutoAppConfigTest {

    @Test
    void basicScan() {
        // Arrange
        // TODO: Initialize test data
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AutoAppConfig.class);

        // Act
        // TODO: Call the method to be tested
        MemberService memberService = ac.getBean(MemberService.class);

        // Assert
        // TODO: Verify the results
        assertThat(memberService).isInstanceOf(MemberService.class);
    }


    /**
     * @ComponentScan 사용하면, 빈 이름은 @Component 달린 클래스명에서 맨 앞을자를 소문자로 바꾼 것으로 자동 등록된다.
     * 만약 빈 등록 명을 직접 정해주려면,
     * @Component("빈 이름")으로 하면 된다.
     */
    @Test
    @DisplayName("AppConfig 사용할 때와 빈 이름 달라졌는지 확인")
    void getBeanNames() {
        // Arrange
        // TODO: Initialize test data
        ApplicationContext ac1 = new AnnotationConfigApplicationContext(AutoAppConfig.class);
        ApplicationContext ac2 = new AnnotationConfigApplicationContext(AppConfig.class);
        // Act
        // TODO: Call the method to be tested
        String[] beanDefinitionNames1 = ac1.getBeanDefinitionNames();
        String[] beanDefinitionNames2 = ac2.getBeanDefinitionNames();
        System.out.println("beanDefinitionNames1 = " + Arrays.stream(beanDefinitionNames1).toList());
        System.out.println("beanDefinitionNames2 = " + Arrays.stream(beanDefinitionNames2).toList());

        // Assert
        // TODO: Verify the results
    }
}
