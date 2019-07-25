/** 
 * Scans for USB-Serial devices, and returns the delta to the last scan result. <p/> This method is synchronized to prevent multiple parallel invocations of this method that could bring the value of lastScanResult into an inconsistent state.
 * @return The delta to the last scan result.
 * @throws IOException if the scan using the {@link UsbSerialScanner} throws an IOException.
 */
public synchronized Delta<UsbSerialDeviceInformation> scan() throws IOException {
  Set<UsbSerialDeviceInformation> scanResult=usbSerialScanner.scan();
  Set<UsbSerialDeviceInformation> added=setDifference(scanResult,lastScanResult);
  Set<UsbSerialDeviceInformation> removed=setDifference(lastScanResult,scanResult);
  Set<UsbSerialDeviceInformation> unchanged=setDifference(scanResult,added);
  lastScanResult=scanResult;
  return new Delta<>(added,removed,unchanged);
}
