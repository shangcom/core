package hello.core.order;

import hello.core.discountPolicy.DiscountPolicy;
import hello.core.discountPolicy.RateDiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;

public class OrderServiceImpl implements OrderService {

    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;

    /**
     *
     * @param memberId
     * @param itemName
     * @param itemPrice
     * @return 위 매개변수 셋  + discountPrice 포함한 Order 객체.
     */
    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }

    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    /**
     *테스트 용도
     * @return
     */
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
