package facadePattern;

public class Test {

    public static void main(String[] args) {

        OrderServiceFacade facade = new OrderServiceFacadeImpl();
        facade.placeOrder(5);

    }
}
