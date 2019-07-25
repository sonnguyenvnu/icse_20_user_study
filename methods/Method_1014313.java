@Override public void initialize(HmDevice device){
  if (device.getType().startsWith(DEVICE_TYPE_19_REMOTE_CONTROL) && !(device.getHmInterface() == HmInterface.CUXD)) {
    addDatapoint(device,18,getName(),HmValueType.STRING,null,false);
  }
}
