package hello.core.order;

import hello.core.discountPolicy.DiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
// 빈 이름 직접 지정하려면
// @Component("빈 이름") <- 이렇게 하면 된다.
public class OrderServiceImpl implements OrderService {

    /**
     * 의존성.
     * private final : 상수, 불변 필드, 종단 필드, 읽기 전용 필드 등으로 부를 수 있음.
     * 만약 생성자가 아니라 setter를 사용해 주입하려면, final을 빼야한다(값이 변경되기 때문)
     */
    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;

    /**
     * @return 매개변수 셋  + discountPrice 포함한 Order 객체.
     */
    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }

    /*
     * setter 사용해서 의존성 주입하는 경우. 의존성이 final이 아닐 때만 사용 가능.
     */
//    @Autowired(required = false)
//    public void setMemberRepository(MemberRepository memberRepository) {
//        System.out.println("memberRepository = " + memberRepository);
//        this.memberRepository = memberRepository;
//    }
//
//    @Autowired(required = false)
//    public void setDiscountPolicy(DiscountPolicy discountPolicy) {
//        this.discountPolicy = discountPolicy;
//    }

    /*
    스프링 빈에 생성자가 하나일 때는 @Autowired 생략해도 자동으로 의존관계 주입 된다.
     */
    @Autowired
    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    /*
     *테스트 용도
     */
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
