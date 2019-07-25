@Override void alert(){
  ConfigModel config=configService.getConfig(DefaultConsoleDbConfig.KEY_SENTINEL_AUTO_PROCESS);
  String user=config.getUpdateUser() == null ? "unkown" : config.getUpdateUser();
  String ip=config.getUpdateIP() == null ? "unkown" : config.getUpdateIP();
  String message=String.format("Sentinel Auto Process will be online %s, </br> " + "Recent update by? %s <br/> and from IP? %s",configService.getAlertSystemRecoverTime().toString(),user,ip);
  alertManager.alert("","",null,ALERT_TYPE.SENTINEL_AUTO_PROCESS_OFF,message);
}
