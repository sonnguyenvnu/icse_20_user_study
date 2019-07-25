@Override public void initialize(HmDevice device){
  if (!device.isGatewayExtras() && !(device.getHmInterface() == HmInterface.CUXD)) {
    addDatapoint(device,0,getName(),HmValueType.BOOL,Boolean.FALSE,false);
  }
}
