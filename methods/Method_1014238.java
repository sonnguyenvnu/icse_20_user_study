/** 
 * Initializes a newly created instance of this class. It tries to set the internal tinyB device, if it isn't yet available, which is done asynchronously as BlueZ can take a while (it seems to do an active scan for the physical device). This method should always be called directly after creating a new object instance.
 */
public synchronized void initialize(){
  scheduler.submit(() -> {
    if (this.device == null) {
      tinyb.BluetoothDevice tinybDevice=findTinybDevice(address.toString());
      if (tinybDevice != null) {
        device=tinybDevice;
        enableNotifications();
      }
    }
 else {
      enableNotifications();
    }
  }
);
}
