@Override public void initialize(HmDevice device){
  if (device.getType().startsWith(DEVICE_TYPE_WIRED_IO_MODULE)) {
    for (    HmChannel channel : device.getChannels()) {
      if (channel.getNumber() >= 7) {
        HmDatapointInfo dpInfoState=HmDatapointInfo.createValuesInfo(channel,DATAPOINT_NAME_STATE);
        HmDatapointInfo dpInfoValue=HmDatapointInfo.createValuesInfo(channel,DATAPOINT_NAME_VALUE);
        boolean hasStateDatapoint=channel.hasDatapoint(dpInfoState);
        boolean hasValueDatapoint=channel.hasDatapoint(dpInfoValue);
        if (hasStateDatapoint && !hasValueDatapoint) {
          HmDatapoint dp=addDatapoint(channel.getDevice(),channel.getNumber(),DATAPOINT_NAME_VALUE,HmValueType.FLOAT,0.0,false);
          dp.setMinValue(0.0);
          dp.setMaxValue(1000.0);
          dp.setVirtual(false);
        }
 else         if (hasValueDatapoint && !hasStateDatapoint) {
          HmDatapoint dp=addDatapoint(channel.getDevice(),channel.getNumber(),DATAPOINT_NAME_STATE,HmValueType.BOOL,false,false);
          dp.setVirtual(false);
        }
      }
      if (channel.getNumber() >= 21) {
        HmDatapointInfo dpInfoCalibration=new HmDatapointInfo(HmParamsetType.MASTER,channel,DATAPOINT_NAME_CALIBRATION);
        if (!channel.hasDatapoint(dpInfoCalibration)) {
          HmDatapoint dp=new HmDatapoint(DATAPOINT_NAME_CALIBRATION,DATAPOINT_NAME_CALIBRATION,HmValueType.INTEGER,0,false,HmParamsetType.MASTER);
          dp.setMinValue(-127);
          dp.setMaxValue(127);
          addDatapoint(channel,dp);
          dp.setVirtual(false);
        }
      }
    }
  }
}
