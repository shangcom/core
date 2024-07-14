package hello.core.lifeCycle;

public class NetworkClient {

    private String url;

    /**
     * 생성자의 connect()와 call()을 afterPropertiesSet() 안에 넣음.
     */
    public NetworkClient() {
        System.out.println("생성자 호출, url = " + url);
//        connect();
//        call("초기화 연결 메시지");
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void connect() {
        System.out.println("connect: " + url);
    }

    public void call(String message) {
        System.out.println("call: " + url + ", message = " + message);
    }

    public void disconnect() {
        System.out.println("close: " + url);
    }

    /**
     * InitializingBean 인터페이스의 메서드
     * afterPropertiesSet : 의존관계 주입이 끝나면 해야할 일.
     */
//    @Override
//    public void afterPropertiesSet() throws Exception {
//        System.out.println("NetworkClient.afterPropertiesSet");
//        connect();
//        call("초기화 연결 메시지");
//    }

    /**
     * DisposableBean 인터페이스의 메서드
     * 스프링 컨테이너는 빈의 생애주기가 끝나기 전에 이 메서드를 호출하여 필요한 자원 해제, 연결 종료 등 정리 작업을 수행.
     * 여기서는 disconnect() 메서드를 호출함.
     */
//    @Override
//    public void destroy() throws Exception {
//        System.out.println("NetworkClient.destroy");
//        disconnect();
//    }

    /*
    위의 afterPropertiesSet(), destroy() 메서드는 스프링에 의존적이기 때문에 스프링을 제외한 라이브러리와 호환이 되지 않음.
    메서드 이름도 수정할 수 없음.
    따라서 해당 인터페이스의 메서드를 사용하는 대신, 직접 빈에서 사용할 콜백함수를 지정해준다.
    NetworkClient 빈에서 사용할 콜백 메서드로 init(), close()를 지정함.
     */
    public void init() {
        System.out.println("NetworkClient.init");
        connect();
        call("초기화 연결 메시지");
    }

    public void close() {
        System.out.println("NetworkClient.close");
        disconnect();
    }
}
