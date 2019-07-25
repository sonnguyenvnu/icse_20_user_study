@Override public void initialize(HmDevice device){
  if (isWirelessDevice(device)) {
    addDatapoint(device,0,getName(),HmValueType.INTEGER,getRssiValue(device.getChannel(0)),true);
  }
}
