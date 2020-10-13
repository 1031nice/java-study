package facadePattern;

/*
The subsystem classes have no reference to the facade.
The classes are not aware of any Facade and are designed to work independently, even if a facade does not exist.
Remember – Subsystem classes are used by the facade, but not the other way around.
 */

public class InventoryService {
    public static boolean isAvailable(Product product){
        /*Check Warehouse database for product availability*/
        return true;
    }
}
