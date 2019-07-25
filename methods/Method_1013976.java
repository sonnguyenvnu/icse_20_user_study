@Override public Set<UsbSerialDeviceInformation> scan() throws IOException {
  Set<UsbSerialDeviceInformation> result=new HashSet<>();
  for (  SerialPortInfo serialPortInfo : getSerialPortInfos()) {
    try {
      UsbSerialDeviceInformation usbSerialDeviceInfo=tryGetUsbSerialDeviceInformation(serialPortInfo);
      if (usbSerialDeviceInfo != null) {
        result.add(usbSerialDeviceInfo);
      }
    }
 catch (    IOException e) {
      logger.warn("Could not extract USB device information for serial port {}: {}",serialPortInfo,e.getMessage());
    }
  }
  return result;
}
