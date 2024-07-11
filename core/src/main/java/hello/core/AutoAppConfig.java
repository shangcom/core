package hello.core;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(
//        basePackages = "hello.core",
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class))
public class AutoAppConfig {
    /*
    * basePackages : 지정한 경로에서부터 컴포넌트 스캔한다. 현재 패키지 구조상 config를 따로 분리해뒀으므로
    * 만약 basePackages를 설정하지 않으면 현재 config 패키지와 그 아래 패키지에 대해서만 스캔하므로
    * member, order 등 다른 패키지에 들어있는 @Component 클래스들 읽어들이지 않는다.
    * 따라서 기본 경로를 최상단인 hello.core으로 설정해야 한다.
    * 일반적으로 이런 설정 필요 없도록 그냥 최상위 경로에 둔다.
    * 경로 복수 지정 가능하다.*/

    /* excludeFilters :
     * @Configuration 어노테이션은 @Component 속성을 포함하고 있다. 즉 @ComponentScan의 대상이 된다.
     * 따라서 여기 예제에서는 중복 등록을 막기 위해 @Configuration를 필터를 통해 제외시킨다.
     */

}
