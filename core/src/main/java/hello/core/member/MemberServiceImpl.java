package hello.core.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MemberServiceImpl implements MemberService {
    /*
     * 인터페이스의 구현체가 하나만 있을 경우, '인터페이스명 + Impl'이 관례임.
     * */

    private MemberRepository memberRepository;

    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long MemberId) {
        return memberRepository.findById(MemberId);
    }

    @Autowired
    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    /*
     * 테스트 용도
     */
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
