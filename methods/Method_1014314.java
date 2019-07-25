@Override public void initialize(HmDevice device){
  if (isDisplay(device)) {
    for (    HmChannel channel : device.getChannels()) {
      if (channel.hasDatapoint(new HmDatapointInfo(HmParamsetType.VALUES,channel,DATAPOINT_NAME_SUBMIT))) {
        for (int i=1; i <= getLineCount(device); i++) {
          addDatapoint(device,channel.getNumber(),DATAPOINT_NAME_DISPLAY_LINE + i,HmValueType.STRING,null,false);
          addEnumDisplayDatapoint(device,channel.getNumber(),DATAPOINT_NAME_DISPLAY_ICON + i,Icon.class);
          if (!isEpDisplay(device)) {
            addEnumDisplayDatapoint(device,channel.getNumber(),DATAPOINT_NAME_DISPLAY_COLOR + i,Color.class);
          }
        }
        if (isEpDisplay(device)) {
          addEnumDisplayDatapoint(device,channel.getNumber(),DATAPOINT_NAME_DISPLAY_BEEPER,Beeper.class);
          HmDatapoint bc=addDatapoint(device,channel.getNumber(),DATAPOINT_NAME_DISPLAY_BEEPCOUNT,HmValueType.INTEGER,1,false);
          bc.setMinValue(0);
          bc.setMaxValue(15);
          HmDatapoint bd=addDatapoint(device,channel.getNumber(),DATAPOINT_NAME_DISPLAY_BEEPINTERVAL,HmValueType.INTEGER,1,false);
          bd.setMinValue(10);
          bd.setMaxValue(160);
          bd.setStep(10);
          addEnumDisplayDatapoint(device,channel.getNumber(),DATAPOINT_NAME_DISPLAY_LED,Led.class);
        }
        addDatapoint(device,channel.getNumber(),DATAPOINT_NAME_DISPLAY_SUBMIT,HmValueType.BOOL,false,false);
      }
    }
  }
}
