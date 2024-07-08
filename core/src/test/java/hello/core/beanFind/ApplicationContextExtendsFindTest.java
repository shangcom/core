package hello.core.beanFind;

import hello.core.discountPolicy.DiscountPolicy;
import hello.core.discountPolicy.FixDiscountPolicy;
import hello.core.discountPolicy.RateDiscountPolicy;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ApplicationContextExtendsFindTest {

    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);


    @Test
    @DisplayName("부모 타입으로 빈 조회시, 자식이 둘 이상 있으면 중복 오류 발생.")
    void findBeanByParentTypeDuplicate() {
        // Arrange
        // TODO: Initialize test data

        // Act
        // TODO: Call the method to be tested

        // Assert
        // TODO: Verify the results
        assertThrows(NoUniqueBeanDefinitionException.class, () -> ac.getBean(DiscountPolicy.class));
    }


    @Test
    @DisplayName("부모 타입으로 조회시, 자식이 둘 이상 있으면, 매개변수로 자식 이름 사용한다.")
    void findBeanByParentTypeBeanName() {
        // Arrange
        // TODO: Initialize test data
        DiscountPolicy rateDiscountPolicy = ac.getBean("rateDiscountPolicy", DiscountPolicy.class);

        // Act
        // TODO: Call the method to be tested

        // Assert
        // TODO: Verify the results
        assertThat(rateDiscountPolicy).isInstanceOf(RateDiscountPolicy.class);
    }

    @Configuration
    static class TestConfig {

        @Bean
        public DiscountPolicy rateDiscountPolicy() {
            return new RateDiscountPolicy();
        }

        @Bean
        public DiscountPolicy fixDiscountPolicy() {
            return new FixDiscountPolicy();
        }
    }
}
