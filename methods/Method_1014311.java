@Override public void initialize(HmDevice device){
  if (!device.isGatewayExtras() && !(device.getHmInterface() == HmInterface.CUXD)) {
    HmDatapoint dp=addDatapoint(device,0,getName(),HmValueType.ENUM,0,false);
    dp.setOptions(new String[]{MODE_LOCKED,MODE_RESET,MODE_FORCE,MODE_DEFER});
    dp.setMinValue(0);
    dp.setMaxValue(dp.getOptions().length - 1);
  }
}
