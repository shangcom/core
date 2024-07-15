package hello.core.web;

import hello.core.common.MyLogger;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequiredArgsConstructor
public class LogDemoController {

    private final LogDemoService logDemoService;
    private final ObjectProvider<MyLogger> myLoggerProvider;

    @RequestMapping("log-demo")
    @ResponseBody
    /*
    * @ResponseBody : 컨트롤러 메서드의 반환값을 HTTP 응답본문으로 변환하는 스프링 어노테이션.
    * 메서드의 반환 값을 직접 HTTP 응답으로 보내기 위해 사용. JSON, XML, 텍스트 등의 형식으로 응답을 반환.
    * 이거 없으면 반환값(OK)를 뷰 이름으로 해석하고, 해당 뷰 이름에 해당하는 템플릿 찾아 렌더링 시도함
    * 여기서는 뷰를 아직 작성하지 않았으므로, 만약 @ResponseBody 지우고 log-demo 요청하면 404 page not found 뜬다.
    * @ResponseBody 있으면, 메서드가 반환하는 문자열이 view 이름으로 해석되지 않고 직접 HTTP 응답 본문에 포함된다.
    * 클라이언트(브라우저)는 HTTP 응답을 받고 본문에 포함된 "OK" 문자열을 화면에 표시.
    * */

    public String logDemo(HttpServletRequest request) {
        String requestURL = request.getRequestURL().toString();
        MyLogger myLogger = myLoggerProvider.getObject();
        myLogger.setRequestURL(requestURL);

        myLogger.log("Controller test");
        logDemoService.logic("testId");
        return "OK";
    }
}
