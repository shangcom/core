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

    /**
     * 생성자 주입을 위한 어노테이션.
     * AppConfig를 사용할 때는 @Bean 메서드에서 의존 관계를 명시적으로 지정했지만,
     * @ComponentScan을 사용하는 경우에는 @Configuration 클래스에 @Bean 메서드가 없기 때문에 의존 관계를 지정할 수 없음.
     * 따라서 @ComponentScan을 사용하는 경우, 스프링 컨테이너에게 의존 관계를 알려주기 위해 구현 클래스의 생성자에 @Autowired를 붙여야 한다.
     * @param memberRepository MemberRepository 타입의 빈을 주입받기 위한 매개변수
     */
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
