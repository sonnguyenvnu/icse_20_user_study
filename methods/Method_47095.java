public boolean checkIfRootIsFromDevice(@NonNull UsbOtgRepresentation device){
  return usbOtgRoot != null && connectedDevice.hashCode() == device.hashCode();
}
