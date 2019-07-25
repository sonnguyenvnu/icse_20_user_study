@Override public void initialize(HmDevice device){
  if (isWirelessDevice(device)) {
    HmDatapoint dp=addDatapoint(device,0,getName(),HmValueType.INTEGER,getRssiValue(device.getChannel(0)),true);
    dp.setUnit("dBm");
    dp.setMinValue(Integer.MIN_VALUE);
    dp.setMaxValue(Integer.MAX_VALUE);
  }
}
