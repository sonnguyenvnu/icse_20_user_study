/** 
 * Updates everything related to USB devices MUST ALWAYS be called after onResume()
 */
@RequiresApi(api=Build.VERSION_CODES.KITKAT) private void updateUsbInformation(){
  boolean isInformationUpdated=false;
  List<UsbOtgRepresentation> connectedDevices=OTGUtil.getMassStorageDevicesConnected(this);
  if (!connectedDevices.isEmpty()) {
    if (SingletonUsbOtg.getInstance().getUsbOtgRoot() != null && OTGUtil.isUsbUriAccessible(this)) {
      for (      UsbOtgRepresentation device : connectedDevices) {
        if (SingletonUsbOtg.getInstance().checkIfRootIsFromDevice(device)) {
          isInformationUpdated=true;
          break;
        }
      }
      if (!isInformationUpdated) {
        SingletonUsbOtg.getInstance().resetUsbOtgRoot();
      }
    }
    if (!isInformationUpdated) {
      SingletonUsbOtg.getInstance().setConnectedDevice(connectedDevices.get(0));
      isInformationUpdated=true;
    }
  }
  if (!isInformationUpdated) {
    SingletonUsbOtg.getInstance().resetUsbOtgRoot();
    drawer.refreshDrawer();
    goToMain(null);
  }
  IntentFilter otgFilter=new IntentFilter();
  otgFilter.addAction(UsbManager.ACTION_USB_DEVICE_ATTACHED);
  otgFilter.addAction(UsbManager.ACTION_USB_DEVICE_DETACHED);
  registerReceiver(mOtgReceiver,otgFilter);
}
