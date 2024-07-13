package hello.core.discountPolicy;

import hello.core.member.Grade;
import hello.core.member.Member;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary
//@Qualifier("fixDiscountPolicy")
// 생성자 주입하는 쪽에서 매개변수로 @Qualifier를 사용해서 fixDiscountPolicy를 지정해버리면 여기서 딱히 이름 지정해줄 필요도 없음.
public class FixDiscountPolicy implements DiscountPolicy{

    /**
     * VIP member 대상 고정할인 : 1000원
     */
    private final int discountFixAmount = 1000;

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
