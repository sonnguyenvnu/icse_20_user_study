@Override public void execute(DsAPI digitalSTROM,String token){
  int value=digitalSTROM.getDeviceOutputValue(token,this.device.getDSID(),null,null,index);
  logger.debug("Device output value on Demand : {}, dSID: {}",value,this.device.getDSID().getValue());
  if (value != 1) {
switch (this.index) {
case 0:
      this.device.updateInternalDeviceState(new DeviceStateUpdateImpl(DeviceStateUpdate.OUTPUT,value));
    return;
case 2:
  this.device.updateInternalDeviceState(new DeviceStateUpdateImpl(DeviceStateUpdate.SLATPOSITION,value));
if (device.isBlind()) {
  value=digitalSTROM.getDeviceOutputValue(token,this.device.getDSID(),null,null,DeviceConstants.DEVICE_SENSOR_SLAT_ANGLE_OUTPUT);
  logger.debug("Device angle output value on Demand : {}, dSID: {}",value,this.device.getDSID().getValue());
  if (value != 1) {
    this.device.updateInternalDeviceState(new DeviceStateUpdateImpl(DeviceStateUpdate.SLAT_ANGLE,value));
  }
}
return;
default :
return;
}
}
}
