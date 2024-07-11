package hello.core.autowired;

import hello.core.member.Member;
import jakarta.annotation.Nullable;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Optional;

public class AutowiredTest {

    @Test
    @DisplayName("@Autowired 옵션에 따른 빈 생성")
    void autowiredOption() {
        // Arrange
        // TODO: Initialize test data
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestBean.class);

        // Act
        // TODO: Call the method to be tested

        // Assert
        // TODO: Verify the results
    }


    /*@Autowired 애노테이션의 required 속성, @Nullable 애노테이션, 그리고 Optional을 사용하여 의존성 주입을 할 때의 동작을 확인.
      Member 타입의 빈이 존재하지 않기 때문에,
      required = false 속성이 적용된 메서드는 호출되지 않고,
      @Nullable이 적용된 메서드는 null이 주입되며,
      Optional이 적용된 메서드는 Optional.empty가 주입됨.
      @Autowired의 빨간줄은 당연한거임. Member이 빈으로 등록되어있지 않으니까 경고 뜨는거.
      여기서는 없는 경우에 어떻게 하라고 옵션을 지정해줬기 때문에 오류 발생하지 않음.
      옵션을 지우면 Member 빈이 존재하지 않기 때문에 테스트 실패할거임.
      */
    static class TestBean {

        /*자동 주입할 대상이 없으면 수정자 메서드 자체가 호출 안됨*/
        @Autowired(required = false)
        public void setNoBean1(Member noBean1) {
            System.out.println("noBean1 = " + noBean1);
        }

        /*자동 주입할 대상이 없으면 null이 입력된다.*/
        @Autowired
        public void setNoBean2(@Nullable Member noBean2) {
            System.out.println("noBean2 = " + noBean2);
        }

        /*자동 주입할 대상이 없으면 `Optional.empty` 가 입력된다.*/
        @Autowired
        public void setNoBean3(Optional<Member> noBean3) {
            System.out.println("noBean3 = " + noBean3);
        }
    }
}
