@Override public void initialize(){
  String port=(String)getConfig().get(SerialButtonBindingConstants.PARAMETER_CONFIG);
  if (port == null) {
    updateStatus(ThingStatus.OFFLINE,ThingStatusDetail.OFFLINE.CONFIGURATION_ERROR,"Port must be set!");
    return;
  }
  portId=serialPortManager.getIdentifier(port);
  if (portId == null) {
    updateStatus(ThingStatus.OFFLINE,ThingStatusDetail.OFFLINE.CONFIGURATION_ERROR,"Port is not known!");
    return;
  }
  try {
    serialPort=portId.open(getThing().getUID().toString(),2000);
    serialPort.addEventListener(this);
    serialPort.notifyOnDataAvailable(true);
    inputStream=serialPort.getInputStream();
    updateStatus(ThingStatus.ONLINE);
  }
 catch (  final IOException ex) {
    updateStatus(ThingStatus.OFFLINE,ThingStatusDetail.OFFLINE.COMMUNICATION_ERROR,"I/O error!");
  }
catch (  PortInUseException e) {
    updateStatus(ThingStatus.OFFLINE,ThingStatusDetail.OFFLINE.COMMUNICATION_ERROR,"Port is in use!");
  }
catch (  TooManyListenersException e) {
    updateStatus(ThingStatus.OFFLINE,ThingStatusDetail.OFFLINE.COMMUNICATION_ERROR,"Cannot attach listener to port!");
  }
}
