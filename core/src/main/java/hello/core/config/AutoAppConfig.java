package hello.core.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
/*
 * @Configuration 어노테이션은 @Component 속성을 포함하고 있다. 즉 @ComponentScan의 대상이 된다.
 * 따라서 여기 예제에서는 중복 등록을 막기 위해 @Configuration를 필터를 통해 제외시킨다.
 */
@ComponentScan(excludeFilters = @ComponentScan.Filter(type= FilterType.ANNOTATION, classes = Configuration.class))
public class AutoAppConfig {

}
