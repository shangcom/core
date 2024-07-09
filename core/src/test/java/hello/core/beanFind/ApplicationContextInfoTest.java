package hello.core.beanFind;

import hello.core.AppConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ApplicationContextInfoTest {

    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

    @Test
    @DisplayName("모든 빈 출력하기")
    void findAllBean() {
        String[] beanDefinitionNames = ac.getBeanDefinitionNames(); // ac(스프링 컨테이너)에 저장된 빈 이름 문자열 배열.
        for (String beanDefinitionName : beanDefinitionNames) {
            Object bean = ac.getBean(beanDefinitionName); // ac에 저장도니 빈 객체의 참조(주소)
            System.out.println("name= " + beanDefinitionName + ", object= " + bean);
        }
    }


    /**
     * 직접 등록한 빈만 출력하기.
     * 전체 빈들 중에서 내가 직접 등록한 빈만 찾으려면, 빈의 메타 정보를 지니고 있는 BeanDefinition 클래스를 사용해야 한다.
     * 개발자가 @Bean이나 @Component 어노테이션을 사용해 등록한 빈은 기본적으로 ROLE_APPLICATION으로 등록된다.
     * ROLE_APPLICATION: 비즈니스 로직과 관련된 애플리케이션 빈
     * ROLE_SUPPORT: 애플리케이션 빈을 지원하는 데 사용되는 빈
     * ROLE_INFRASTRUCTURE: 스프링 프레임워크 자체에서 사용하는 빈
     * 따라서 ROLE_APPLICATION과 일치하는 빈들을 출력해보면 된다.
     */
    @Test
    @DisplayName("애플리케이션 빈 출력하기")
    void findApplicationBean() {
        String[] beanDefinitionNames = ac.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {
            BeanDefinition beanDefinition = ac.getBeanDefinition(beanDefinitionName);

            if (beanDefinition.getRole() == BeanDefinition.ROLE_APPLICATION) {
                Object bean = ac.getBean(beanDefinitionName);
                System.out.println("name= " + beanDefinitionName + ", object= " + bean);
            }
        }
    }
}
