@Modified public synchronized void modified(Map<String,Object> config){
  String primaryAddressConf=(String)config.get(PRIMARY_ADDRESS);
  String oldPrimaryAddress=primaryAddress;
  if (primaryAddressConf == null || primaryAddressConf.isEmpty() || !isValidIPConfig(primaryAddressConf)) {
    primaryAddress=getFirstLocalIPv4Address();
  }
 else {
    primaryAddress=primaryAddressConf;
  }
  notifyPrimaryAddressChange(oldPrimaryAddress,primaryAddress);
  String broadcastAddressConf=(String)config.get(BROADCAST_ADDRESS);
  if (broadcastAddressConf == null || broadcastAddressConf.isEmpty() || !isValidIPConfig(broadcastAddressConf)) {
    configuredBroadcastAddress=getPrimaryBroadcastAddress();
  }
 else {
    configuredBroadcastAddress=broadcastAddressConf;
  }
  useOnlyOneAddress=getConfigParameter(config,USE_ONLY_ONE_ADDRESS,false);
  useIPv6=getConfigParameter(config,USE_IPV6,true);
  Object pollIntervalSecondsObj=null;
  int pollIntervalSeconds=POLL_INTERVAL_SECONDS;
  try {
    pollIntervalSecondsObj=config.get(POLL_INTERVAL);
    if (pollIntervalSecondsObj != null) {
      pollIntervalSeconds=Integer.parseInt(pollIntervalSecondsObj.toString());
    }
  }
 catch (  NumberFormatException e) {
    LOGGER.warn("Cannot parse value {} from key {}, will use default {}",pollIntervalSecondsObj,POLL_INTERVAL,pollIntervalSeconds);
  }
  scheduleToPollNetworkInterface(pollIntervalSeconds);
}
