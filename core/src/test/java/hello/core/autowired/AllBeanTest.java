package hello.core.autowired;

import hello.core.AutoAppConfig;
import hello.core.discountPolicy.DiscountPolicy;
import hello.core.member.Grade;
import hello.core.member.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class AllBeanTest {

    @Test
    void findAllBeans() {
        // Arrange
        // TODO: Initialize test data
        ApplicationContext ac = new AnnotationConfigApplicationContext(AutoAppConfig.class);

        DiscountService discountService = ac.getBean(DiscountService.class);
        Member member = new Member(1L, "userA", Grade.VIP);

        // Act
        // TODO: Call the method to be tested
        int discountPrice = discountService.discount(member, 10000, "fixDiscountPolicy");

        // Assert
        // TODO: Verify the results
        assertThat(discountService).isInstanceOf(DiscountService.class);
        assertThat(discountPrice).isEqualTo(1000);
    }

    @Test
    void findAllBeans2() {
        // Arrange
        // TODO: Initialize test data
        ApplicationContext ac = new AnnotationConfigApplicationContext(AutoAppConfig.class);

        DiscountService discountService = ac.getBean(DiscountService.class);
        Member member = new Member(1L, "userA", Grade.VIP);

        // Act
        // TODO: Call the method to be tested
        /**
         * discountCode로 mainDiscountPolicy가 아니라 rateDiscountPolicy 사용하면 nullPoint Exception 터진다.
         * 애초에 빈 등록 자체를 커스텀 어노테이션의 @Qualify를 통해 mainDiscountPolicy로 해뒀기 때문에,
         * rateDiscountPolicy라는 빈 자체가 등록되어있지 않기 때문이다.
         */
        int rateDiscountPrice = discountService.discount(member, 20000, "mainDiscountPolicy");

        // Assert
        // TODO: Verify the results
        assertThat(discountService).isInstanceOf(DiscountService.class);
        assertThat(rateDiscountPrice).isEqualTo(2000);
    }

    /**
     * DiscountService가 빈으로 등록됨.
     * DiscountService는 Map으로 모든 DiscountPolicy(rate, fix)를 주입받는다(생성자 주입)
     *
     */

    /**
    @Service : 이 어노테이션 붙이면 자동으로 DiscountService 클래스가 스프링 빈으로 등록됨.
    따라서 AnnotationConfigApplicationContext 생성할 때, 생성자의 매개변수로 이 클래스 전달할 필요 없음.
    만약 @Service 어노테이션 없애면, AnnotationConfigApplicationContext 생성할 때 DiscountService.class도 매개변수로 넘겨야함.
      */
    @Service
    static class DiscountService {
        private final Map<String, DiscountPolicy> policyMap;

        /**
         * @Autowired는 DiscountService의 생성자에서 Map<String, DiscountPolicy> 타입의 매개변수를 주입받을 때,
         * 스프링 컨테이너에 등록된 모든 DiscountPolicy 타입의 빈을 수집하여 Map으로 만들어 주입.
         * 설명 :
         * Map<String, 특정 타입> 타입의 매개변수를 주입할 때, 자동으로 특정 타입의 모든 빈을 찾아 Map으로 만들어 주입함.
         * 이때 String은 자동으로 해당 빈의 이름(클래스 명 맨 앞글자를 소문자로 바꾼 것)으로 지정됨.
         * 즉, map의 key는 특정 타입 빈의 등록 이름이고, value는 해당 빈의 인스턴스이다.
         */
        @Autowired // 생성자 하나라서 생략 가능. 만약 @Service 떼면 스프링이 관리하지 않는 클래스가 되며, 이 어노테이션도 제거해야함.
        public DiscountService(Map<String, DiscountPolicy> policyMap) {
            this.policyMap = policyMap;
            System.out.println("policyMap = " + policyMap);
        }

        /**
         * @param discountCode 여기서 넘어온 정책 이름을 키로 Map(policyMap)에서 해당하는 정책 빈을 찾고,
         * @return 해당 정책 빈의 discount() 메서드를 실행하여 결과값을 반환.
         */
        public int discount(Member member, int price, String discountCode) {
            DiscountPolicy discountPolicy = policyMap.get(discountCode);
            return discountPolicy.discount(member, price);
        }
    }

}
