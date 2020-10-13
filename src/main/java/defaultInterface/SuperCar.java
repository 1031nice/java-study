package defaultInterface;

public class SuperCar implements Car {
    @Override
    public void accelerate() {
        System.out.println("accelerate!");
    }
}
