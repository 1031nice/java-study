package designpattern.adapter;

// client
public class Client {
    public static void main(String[] args) {
        ThunderboltCharger thunderboltCharger = new ThunderboltCharger();
        UsbcCharger usbcCharger = new UsbcCharger();

        System.out.println(thunderboltCharger.chargeIphone());
        System.out.println(usbcCharger.chargeGalaxy());

        System.out.println("=========================");

        IphoneCharger thunderboltToUsbcAdapter = new ThunderboltToUsbcAdapter(usbcCharger);
        System.out.println(thunderboltToUsbcAdapter.chargeIphone());
    }
}

