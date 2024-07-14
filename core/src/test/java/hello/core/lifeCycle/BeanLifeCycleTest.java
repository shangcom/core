package hello.core.lifeCycle;

import org.junit.jupiter.api.Test;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class BeanLifeCycleTest {

    @Test
    void lifeCycleTest() {
        // Arrange
        // TODO: Initialize test data
        ConfigurableApplicationContext ac = new AnnotationConfigApplicationContext(LifeCycleConfig.class);
        NetworkClient client = ac.getBean(NetworkClient.class);
        // Act
        // TODO: Call the method to be tested
        ac.close();

    }

    @Configuration
    static class LifeCycleConfig {

        /**
         * destroyMethod는 기본값이 "(inferred)"(추론)로, 일반적으로 많이 사용하는 메서드명인 close, shutdown 등 메서드가
         * 빈으로 등록할 클래스에 존재할 경우 자동으로 인식해준다.
         */
        @Bean(initMethod = "init", destroyMethod = "close")
        public NetworkClient networkClient() {
            NetworkClient networkClient = new NetworkClient();
            networkClient.setUrl("http://hello-spring.dev");
            return networkClient;
        }
    }
}
