package hello.core.member;


public class MemberServiceImpl implements MemberService {
    /*
     * 인터페이스의 구현체가 하나만 있을 경우, '인터페이스명 + Impl'이 관례임.
     * */

    private MemberRepository memberRepository = new MemoryMemberRepository();

    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long MemberId) {
        return memberRepository.findById(MemberId);
    }
}
