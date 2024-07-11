package hello.core.order;

import hello.core.discountPolicy.FixDiscountPolicy;
import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class OrderServiceImplTest {

    @Test
    void createOrder() {
        // Arrange
        // TODO: Initialize test data
        MemoryMemberRepository memberRepository = new MemoryMemberRepository();
        memberRepository.save(new Member(1L, "name", Grade.VIP));
        OrderServiceImpl orderService = new OrderServiceImpl(memberRepository, new FixDiscountPolicy());
        // Act
        // TODO: Call the method to be tested
        Order order = orderService.createOrder(1L, "itemA", 10000);

        // Assert
        // TODO: Verify the results
        Assertions.assertThat(order.getDiscountPrice()).isEqualTo(1000);
    }
}
