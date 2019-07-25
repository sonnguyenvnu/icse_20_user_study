@Override public void initialize(HmDevice device){
  if (device.isGatewayExtras()) {
    addDatapoint(device,0,getName(),HmValueType.BOOL,Boolean.FALSE,false);
  }
}
