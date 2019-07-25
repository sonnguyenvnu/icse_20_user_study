@Override public void initialize(HmDevice device){
  String batteryType=batteries.getProperty(device.getType());
  if (batteryType != null) {
    addDatapoint(device,0,getName(),HmValueType.STRING,batteryType,true);
  }
}
