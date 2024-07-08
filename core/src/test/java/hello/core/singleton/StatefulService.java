package hello.core.singleton;

public class StatefulService {

    private int price;

    /**
     * this.price = price;
     * 이 부분에서 문제 발생함.
     * @param name : 주문자명
     * @param price
     */
    public void order(String name, int price) {
        System.out.println("name = " + name + ", price = " + price);
        this.price = price;
    }
    public int getPrice() {
        return price;
    }
}
