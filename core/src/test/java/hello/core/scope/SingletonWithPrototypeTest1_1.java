package hello.core.scope;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import jakarta.inject.Provider;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import static org.assertj.core.api.Assertions.assertThat;

public class SingletonWithPrototypeTest1_1 {

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

        int count1 = clientBean1.logic2();

        // Assert
        // TODO: Verify the results
        assertThat(count1).isEqualTo(1);
        ClientBean clientBean2 = ac.getBean(ClientBean.class);
        int count2 = clientBean2.logic2();
//        assertThat(count2).isEqualTo(2);
//        prototypeBean을 그때그때 주입받는 것으로 바꾸고 난 뒤에는 1이 나온다.
        assertThat(count2).isEqualTo(1);
    }

    /*ObjectProvider, 싱글톤 빈에서 프로토타입 스코프의 빈을 요청할 때 매번 새로운 인스턴스를 생성해서 주입받기 위해 사용*/

    @Scope("singleton")
    static class ClientBean {

        // 스프링에 의존함. 기능 다양.
//        @Autowired
//        private ObjectProvider<PrototypeBean> prototypeBeanObjectProvider;

        // 자바 표준. 기능 오직 get() 하나.
        @Autowired
        private Provider<PrototypeBean> provider;

        /*
         * ObjectProvider, jakarta.inject :
         * DL(Dependency Lookup) 기능 제공.
         * 싱글톤 빈에서 프로토타입 스코프의 빈을 요청할 때 매번 새로운 인스턴스를 생성해서 주입받기 위해 사용.
         */
//        public int logic() {
//            PrototypeBean prototypeBean = prototypeBeanObjectProvider.getObject();
//            prototypeBean.addCount();
//            return prototypeBean.getCount();
//        }

        /**
         * javax.provider 사용
         */
        public int logic2() {
            PrototypeBean prototypeBean = provider.get();
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
