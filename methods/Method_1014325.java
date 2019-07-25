@Override public void initialize(HmDevice device){
  if (isApplicable(device)) {
    HmChannel channelOne=device.getChannel(1);
    if (channelOne != null) {
      HmDatapointInfo dpStateInfo=HmDatapointInfo.createValuesInfo(channelOne,DATAPOINT_NAME_STATE);
      HmDatapoint dpState=channelOne.getDatapoint(dpStateInfo);
      if (dpState != null) {
        addDatapoint(device,1,getName(),HmValueType.BOOL,convertState(dpState.getValue()),true);
      }
    }
  }
}
