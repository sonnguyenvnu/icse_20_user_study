private void init(){
  if (groupList.contains((short)1)) {
    maxOutputValue=DeviceConstants.MAX_OUTPUT_VALUE_LIGHT;
    if (this.isDimmable()) {
      minOutputValue=DeviceConstants.MIN_DIM_VALUE;
    }
  }
 else {
    maxOutputValue=DeviceConstants.DEFAULT_MAX_OUTPUTVALUE;
    minOutputValue=0;
  }
  if (isOn) {
    outputValue=DeviceConstants.DEFAULT_MAX_OUTPUTVALUE;
  }
}
