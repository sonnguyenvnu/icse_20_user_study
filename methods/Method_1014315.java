@Override public void initialize(HmDevice device){
  if (!device.isGatewayExtras()) {
    addDatapoint(device,0,getName(),HmValueType.STRING,device.getFirmware(),true);
  }
}
