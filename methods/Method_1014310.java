@Override public void initialize(HmDevice device){
  for (  HmChannel channel : device.getChannels()) {
    if (channel.hasPressDatapoint()) {
      HmDatapoint dp=addDatapoint(device,channel.getNumber(),getName(),HmValueType.STRING,null,false);
      dp.setTrigger(true);
      dp.setOptions(new String[]{CommonTriggerEvents.SHORT_PRESSED,CommonTriggerEvents.LONG_PRESSED,CommonTriggerEvents.DOUBLE_PRESSED});
    }
  }
}
