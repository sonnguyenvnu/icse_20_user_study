/** 
 * Checks if there is at least one USB device connected with class MASS STORAGE.
 */
@NonNull public static List<UsbOtgRepresentation> getMassStorageDevicesConnected(@NonNull final Context context){
  UsbManager usbManager=(UsbManager)context.getSystemService(USB_SERVICE);
  if (usbManager == null)   return Collections.emptyList();
  HashMap<String,UsbDevice> devices=usbManager.getDeviceList();
  ArrayList<UsbOtgRepresentation> usbOtgRepresentations=new ArrayList<>();
  for (  String deviceName : devices.keySet()) {
    UsbDevice device=devices.get(deviceName);
    for (int i=0; i < device.getInterfaceCount(); i++) {
      if (device.getInterface(i).getInterfaceClass() == UsbConstants.USB_CLASS_MASS_STORAGE) {
        final @Nullable String serial=Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP ? device.getSerialNumber() : null;
        UsbOtgRepresentation usb=new UsbOtgRepresentation(device.getProductId(),device.getVendorId(),serial);
        usbOtgRepresentations.add(usb);
      }
    }
  }
  return usbOtgRepresentations;
}
