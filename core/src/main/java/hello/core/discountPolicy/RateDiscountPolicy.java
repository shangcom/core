package hello.core.discountPolicy;

import hello.core.annotation.MainDiscountPolicy;
import hello.core.member.Grade;
import hello.core.member.Member;
import org.springframework.stereotype.Component;

@Component("mainDiscountPolicy") // 이거나 바로 아래 @Qualifier를 통해 빈 이름 지정하는 거나 같음. 그래도 이 방법이 맞음.
//@Qualifier("mainDiscountPolicy") // 빈 이름 "mainDiscountPolicy"로 등록
@MainDiscountPolicy
public class RateDiscountPolicy implements DiscountPolicy{

    private final int discountPercent = 10;
    @Override
    public int discount(Member member, int price) {
        if (member.getGrade() == Grade.VIP) {
            return price * discountPercent / 100;
        } else {
            return 0;
        }
    }
}
