package hello.core.annotation;

import org.springframework.beans.factory.annotation.Qualifier;

import java.lang.annotation.*;

@Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER, ElementType.TYPE, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
@Qualifier("mainDiscountPolicy")
public @interface MainDiscountPolicy {
/*
커스텀 어노테이션.
@Qualifier("mainDiscountPolicy")를 직접 사용하면 컴파일 타임에 검증되지 않아, 빈 이름에 오타가 있을 경우 컴파일 오류가 아닌 런타임 오류가 발생.
이를 방지하기 위해, @Qualifier를 포함하는 커스텀 애노테이션을 만들어 사용함.
어노테이션의 잘못된 사용은 컴파일 타임에 잡아낼 수 있기 때문임.
*/
}
