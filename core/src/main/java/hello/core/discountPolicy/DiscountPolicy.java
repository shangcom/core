package hello.core.discountPolicy;

import hello.core.member.Member;

public interface DiscountPolicy {
    /**
     *
     * @param member
     * @param price 상품 가격
     * @return 할인 대상 금액
     */
    int discount(Member member, int price);
}
