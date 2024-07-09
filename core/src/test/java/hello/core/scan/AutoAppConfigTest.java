package hello.core.scan;

import hello.core.AutoAppConfig;
import hello.core.member.MemberService;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

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
}
