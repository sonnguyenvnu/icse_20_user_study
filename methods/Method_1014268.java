@Override public void execute(DsAPI digitalSTROM,String token){
  int consumption=digitalSTROM.getDeviceSensorValue(token,this.device.getDSID(),null,null,device.getSensorIndex(sensorType));
  logger.debug("Executes {} new device consumption is {}",this.toString(),consumption);
  if (updateDevice) {
    device.setDeviceSensorDsValueBySensorJob(sensorType,consumption);
  }
}
