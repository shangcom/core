package hello.core.singleton;

import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import static org.assertj.core.api.Assertions.assertThat;

class StatefulServiceTest {

    @Test
    void statefulServiceSingleton() {
        // Arrange
        // TODO: Initialize test data
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(Testonfig.class);
        StatefulService statefulService1 = ac.getBean(StatefulService.class);
        StatefulService statefulService2 = ac.getBean(StatefulService.class);

        // Act : A 사용자가 만원, B 사용자가 2만원을 각각 주문함.
        // TODO: Call the method to be tested
        statefulService1.order("userA", 10000);
        statefulService2.order("userB", 20000);

        // Assert : 객체를 공유하고 있기 때문에 B 사용자의 정보로 덮어씌워졌음. 따라서 만원이 아니라 2만원이 조회된다.
        // TODO: Verify the results
        assertThat(statefulService1.getPrice()).isNotEqualTo(10000);
    }

    static class Testonfig {

        @Bean
        public StatefulService statefulService() {
            return new StatefulService();
        }
    }
}