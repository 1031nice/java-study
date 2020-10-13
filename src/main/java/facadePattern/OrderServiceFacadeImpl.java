package facadePattern;

public class OrderServiceFacadeImpl implements OrderServiceFacade {
    @Override
    public boolean placeOrder(int pId) {
        boolean orderFulfilled=false;
        Product product = new Product(1, "phone");
        if(InventoryService.isAvailable(product)) // inventory
        {
            System.out.println("Product with ID: " + product.productId + " is available.");
            boolean paymentConfirmed = PaymentService.makePayment(); // payment
            if(paymentConfirmed){
                System.out.println("Payment confirmed...");
                ShippingService.shipProduct(product); // shipping
                System.out.println("Product shipped...");
                orderFulfilled = true;
            }
        }
        return orderFulfilled;
    }
}
