package hello.core.discountPolicy;

import hello.core.member.Grade;
import hello.core.member.Member;

public class FixDiscountPolicy implements DiscountPolicy{

    /**
     * VIP member 대상 고정할인 : 1000원
     */
    private int discountFixAmount = 1000;

    /**
     *
     * @param member : Grade만 넘겨도 되지만, 미래 확장성을 위해 Member 객체를 통으로 넘겼음.
     * @param price 상품 가격
     * @return : 할인 적용된 가격.
     */
    @Override
    public int discount(Member member, int price) {
        if (member.getGrade() == Grade.VIP) {
            return discountFixAmount;
        } else {
            return 0;
        }
    }

}
