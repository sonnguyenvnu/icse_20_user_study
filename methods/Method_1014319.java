@Override public void initialize(HmDevice device){
  for (  HmChannel channel : device.getChannels()) {
    HmDatapointInfo dpInfoOnTime=HmDatapointInfo.createValuesInfo(channel,DATAPOINT_NAME_ON_TIME);
    if (channel.hasDatapoint(dpInfoOnTime)) {
      HmDatapointInfo dpInfoLevel=HmDatapointInfo.createValuesInfo(channel,DATAPOINT_NAME_LEVEL);
      HmDatapointInfo dpInfoState=HmDatapointInfo.createValuesInfo(channel,DATAPOINT_NAME_STATE);
      if (channel.hasDatapoint(dpInfoLevel) || channel.hasDatapoint(dpInfoState)) {
        HmDatapoint dpOnTime=channel.getDatapoint(dpInfoOnTime);
        HmDatapoint dpOnTimeAutomatic=dpOnTime.clone();
        dpOnTimeAutomatic.setName(getName());
        dpOnTimeAutomatic.setDescription(getName());
        addDatapoint(channel,dpOnTimeAutomatic);
      }
    }
  }
}
