package designpattern.adapter;

public class ThunderboltCharger implements IphoneCharger {
    @Override
    public String chargeIphone() {
        return "charge Iphone using thunderbolt";
    }
}

