package hello.core.order;

import hello.core.discountPolicy.DiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor // final이나 @NonNulll 선언된 필드는 필수값이고, 이 어노테이션은 필수값을 파라미터로 받는 생성자를 자동으로 만들어준다.

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
////        System.out.println("memberRepository = " + memberRepository);
//        this.memberRepository = memberRepository;
//    }
//
//    @Autowired(required = false)
//    public void setDiscountPolicy(DiscountPolicy discountPolicy) {
//        this.discountPolicy = discountPolicy;
//    }

    /*
    생성자 주입.
    스프링 빈에 생성자가 하나일 때는 @Autowired 생략해도 자동으로 의존관계 주입 된다.
    @RequiredArgsConstructor가 이 생성자를 그대로 만들어 준다.
     */
//    @Autowired // 생략 가능
//    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
//        this.memberRepository = memberRepository;
//        this.discountPolicy = discountPolicy;
//    }

    /**
     * 메서드 주입.
     * 특정한 시점에만 필요한 의존성일 경우, 메서드 호출을 통해 해당 의존성을 주입함.
     * 생성자 주입은 애플리케이션이 실행되면 바로 자동으로 주입하지만, 메서드 주입은 해당 메서드를 호출해야지 의존성 주입이 된다.
     * 여러 의존성을 한번에 처리할 수 있다.
     * final 필드에는 적용 불가.
     */
//    @Autowired
//    public void init(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
//        this.memberRepository = memberRepository;
//        this.discountPolicy = discountPolicy;
//    }

    /*
     *테스트 용도
     */
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
