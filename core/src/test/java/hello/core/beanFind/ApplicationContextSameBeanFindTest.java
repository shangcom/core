package hello.core.beanFind;

import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ApplicationContextSameBeanFindTest {

    /*
    * 예시 위해 AppConfig 대신 static class로 새로운 @Configuration 클래스 만들었음
    * SameBeanConfig
    * */
    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(SameBeanConfig.class);


    /**
     * NoUniqueBeanDefinitionException 발생함.
     * 아래 SameBeanConfig에 정의된 bean을 보면, 빈 이름은 다르지만(memberRepository1, 2) 객체 타입이 같다.
     * 빈 이름 하나에 클래스 하나 할당되어야하는데, 지금은
     * 이럴 때는 bean의 name 속성 활용해서 bean이 등록될 때 이름을 서로 다르게 한다.
     */
    @Test
    @DisplayName("타입으로 조회시 같은 타입이 둘 이상 있으면, 중복 오류가 발생함")
    void findBeanByTypeDuplicate() {
        // Arrange
        // TODO: Initialize test data

        // Act
        // TODO: Call the method to be tested

        // Assert
        // TODO: Verify the results
        assertThrows(NoUniqueBeanDefinitionException.class, () -> ac.getBean(MemberRepository.class));
    }

    @Test
    @DisplayName("타입으로 조회시 같은 타입이 둘 이상 있으면, 빈 이름을 지정하면 된다.")
    void findBeanByName() {
        // Arrange
        // TODO: Initialize test data
        MemberRepository bean = ac.getBean("memberRepository1", MemberRepository.class);
        // Act
        // TODO: Call the method to be tested

        // Assert
        // TODO: Verify the results
        assertThat(bean).isInstanceOf(MemberRepository.class);
    }

    @Test
    @DisplayName("특정 타입 빈 모두 조회")
    void findAllBeanByType() {
        // Arrange
        // TODO: Initialize test data
        Map<String, MemberRepository> beansOfType = ac.getBeansOfType(MemberRepository.class);

        // Act
        // TODO: Call the method to be tested
        for (String key : beansOfType.keySet()) {
            System.out.println("key = " + key + " value = " + beansOfType.get(key));
        }
        System.out.println("beansOfType = " + beansOfType);

        // Assert
        // TODO: Verify the results
        assertThat(beansOfType.size()).isEqualTo(2);
    }

    /**
     * 이름은 다르지만 동일한 타입 빈을 여러개 등록했음. 실제로는 쓰지 않는 방법임. 그냥 예외 발생시키려는 예시일 뿐.
     * 즉, 빈 이름만 다르고 나머지는 전부 똑같은 상황인데, 아무런 의미도 없음.
     * 제대로 된 코드라면, 이름은 같아도 전략 패턴(주입하는 의존성이 서로 다르다던지)은 달라야한다.
     */
    @Configuration
    static class SameBeanConfig {

        @Bean
        public MemberRepository memberRepository1() {
            return new MemoryMemberRepository();
        }

        @Bean
        public MemberRepository memberRepository2() {
            return new MemoryMemberRepository();
        }
    }
}
