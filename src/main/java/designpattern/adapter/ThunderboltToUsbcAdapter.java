package designpattern.adapter;

// adapter
public class ThunderboltToUsbcAdapter implements IphoneCharger {
    UsbcCharger usbcCharger;

    public ThunderboltToUsbcAdapter(UsbcCharger usbcCharger) {
        this.usbcCharger = usbcCharger;
    }

    @Override
    public String chargeIphone() {
        return usbcCharger.chargeGalaxy() + "(using adapter)";
    }
}

