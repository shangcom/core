package hello.core.xml;

import hello.core.member.MemberService;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;

public class XmlAppContext {

    @Test
    void xmlAppContext() {
        // Arrange
        // TODO: Initialize test data
        ApplicationContext ac = new GenericXmlApplicationContext("appConfig.xml");
        // Act
        // TODO: Call the method to be tested
        MemberService memberService = ac.getBean("memberService", MemberService.class);
        // Assert
        // TODO: Verify the results
        assertThat(memberService).isInstanceOf(MemberService.class);
    }
}
