package hello.core.discountPolicy;

import hello.core.member.Grade;
import hello.core.member.Member;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class RateDiscountPolicyTest {

    RateDiscountPolicy rateDiscountPolicy = new RateDiscountPolicy();

    @Test
    @DisplayName("VIP는 10% 할인 적용되어야 한다.")
    void vip_o() {
        // Arrange
        // TODO: Initialize test data
        Member member = new Member(1L, "memberVIP", Grade.VIP);
        int price = 10000;

        // Act
        // TODO: Call the method to be tested
        int discount = rateDiscountPolicy.discount(member, price);

        // Assert
        // TODO: Verify the results
        assertThat(discount).isEqualTo(1000);
    }

    @Test
    @DisplayName("VIP가 아니면 할인 적용 X")
    void vip_x() {
        // Arrange
        // TODO: Initialize test data
        Member member = new Member(2L, "memberBASIC", Grade.BASIC);
        int itemPrice = 10000;

        // Act
        // TODO: Call the method to be tested
        int discount = rateDiscountPolicy.discount(member, itemPrice);

        // Assert
        // TODO: Verify the results
        assertThat(discount).isEqualTo(0);
    }
}