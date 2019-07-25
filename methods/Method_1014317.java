@Override public void initialize(HmDevice device){
  if (device.isGatewayExtras()) {
    HmDatapoint dp=addDatapoint(device,0,getName(),HmValueType.INTEGER,60,false);
    dp.setMinValue(10);
    dp.setMaxValue(300);
  }
}
