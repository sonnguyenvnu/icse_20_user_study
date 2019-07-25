@Override public void initialize(HmDevice device){
  if (device.isGatewayExtras()) {
    addDatapoint(device,HmChannel.CHANNEL_NUMBER_EXTRAS,getName(),HmValueType.BOOL,Boolean.FALSE,false);
  }
}
