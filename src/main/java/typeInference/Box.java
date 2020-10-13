package typeInference;

public class Box<U> {

    private U u;

    public void set(U u) {
        this.u = u;
    }

    public U get() {
        return u;
    }
}
