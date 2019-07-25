@Override public void execute(DsAPI digitalSTROM,String token){
  DeviceSceneSpec sceneConfig=digitalSTROM.getDeviceSceneMode(token,device.getDSID(),null,null,sceneID);
  if (sceneConfig != null) {
    device.addSceneConfig(sceneID,sceneConfig);
    logger.debug("UPDATED scene configuration for dSID: {}, sceneID: {}, configuration: {}",this.device.getDSID(),sceneID,sceneConfig);
  }
}
