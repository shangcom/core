package hello.core.scope;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import static org.assertj.core.api.Assertions.assertThat;

public class SingletonWithPrototypeTest1 {

    @Test
    void prototypeFind() {
        // Arrange
        // TODO: Initialize test data
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(PrototypeBean.class);

        // Act
        // TODO: Call the method to be tested
        PrototypeBean prototypeBean1 = ac.getBean(PrototypeBean.class);
        prototypeBean1.addCount();

        PrototypeBean prototypeBean2 = ac.getBean(PrototypeBean.class);
        prototypeBean2.addCount();

        // Assert
        // TODO: Verify the results
        assertThat(prototypeBean1.getCount()).isEqualTo(1);
        assertThat(prototypeBean2.getCount()).isEqualTo(1);

    }

    /**
     * 매번 새로 주입받아 사용하는게 아니라, ClientBean이 최초 생성될 때 주입된 PrototypeBean을 계속 사용하고 있음.
     * 따라서 count 값이 유지됨.
     * 이를 방지하기 위해서는, 즉 prototype 스코프의 속성을 제대로 사용하기 위해서는 ClientBean을 요청할 때마다
     * 새로 prototypeBean이 만들어져서 주입되어야한다.
     */
    @Test
    void singletonClientUsePrototype() {
        // Arrange
        // TODO: Initialize test data
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(ClientBean.class, PrototypeBean.class);

        // Act
        // TODO: Call the method to be tested
        ClientBean clientBean1 = ac.getBean(ClientBean.class);

        int count1 = clientBean1.logic();

        // Assert
        // TODO: Verify the results
        assertThat(count1).isEqualTo(1);
        ClientBean clientBean2 = ac.getBean(ClientBean.class);
        int count2 = clientBean2.logic();
//        assertThat(count2).isEqualTo(2);
//        prototypeBean을 그때그때 주입받는 것으로 바꾸고 난 뒤에는 1이 나온다.
        assertThat(count2).isEqualTo(1);
    }


    @Scope("singleton")
//    @RequiredArgsConstructor // 이 어노테이션으로 아래 생성자 대체 가능.
    static class ClientBean {

        /*여기서 프로토타입빈을 주입받으면 계속 유지돼버림. */
//        private final PrototypeBean prototypeBean;
//        @Autowired
//        public ClientBean(PrototypeBean prototypeBean) {
//            this.prototypeBean = prototypeBean;
//        }

/*아예 ApplicationContet를 logic메서드가 호출될 때마다 생성해서 PrototypeBean을 새로 등록하고, 새로 빈을 꺼내서 사용하면 되긴 함.*/
        @Autowired
        ApplicationContext applicationContext;

        public int logic() {
            PrototypeBean prototypeBean = applicationContext.getBean(PrototypeBean.class);
            prototypeBean.addCount();
            return prototypeBean.getCount();
        }
    }

    @Scope("prototype")
    static class PrototypeBean {
        private int count = 0;

        public void addCount() {
            count++;
        }

        public int getCount() {
            return count;
        }

        @PostConstruct
        public void init() {
            System.out.println("PrototypeBean.init: " + this);
        }

        @PreDestroy
        public void destroy() {
            System.out.println("PrototypeBean.destroy");
        }
    }
}
