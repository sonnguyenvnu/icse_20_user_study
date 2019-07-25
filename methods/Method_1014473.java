@ReactMethod public void read(String deviceUUID,String serviceUUID,String characteristicUUID,Callback callback){
  Log.d(LOG_TAG,"Read from: " + deviceUUID);
  Peripheral peripheral=peripherals.get(deviceUUID);
  if (peripheral != null) {
    peripheral.read(UUIDHelper.uuidFromString(serviceUUID),UUIDHelper.uuidFromString(characteristicUUID),callback);
  }
 else   callback.invoke("Peripheral not found",null);
}
