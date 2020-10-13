package defaultInterface;

public interface Car {

    void accelerate();

    default void fly() {
        System.out.println("fly!");
    }

}
