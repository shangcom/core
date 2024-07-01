package hello.core.member;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class MemberServiceTest {

    MemberService memberService = new MemberServiceImpl();

    @Test
    void join() {
        // Arrange
        // TODO: Initialize test data
        Member member = new Member(1L, "member1", Grade.BASIC);

        // Act
        // TODO: Call the method to be tested
        memberService.join(member);
        Member findMember = memberService.findMember(member.getId());

        // Assert
        // TODO: Verify the results
        Assertions.assertThat(member).isEqualTo(findMember);
    }
}
