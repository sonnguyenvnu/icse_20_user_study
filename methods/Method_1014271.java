@Override public void execute(DsAPI digitalSTROM,String token){
  int[] sceneValue=digitalSTROM.getSceneValue(token,this.device.getDSID(),null,null,this.sceneID);
  if (sceneValue[0] != -1) {
    if (device.isBlind()) {
      device.setSceneOutputValue(this.sceneID,sceneValue[0],sceneValue[1]);
    }
 else {
      device.setSceneOutputValue(this.sceneID,sceneValue[0]);
    }
    logger.debug("UPDATED sceneOutputValue for dsid: {}, sceneID: {}, value: {}, angle: {}",this.device.getDSID(),sceneID,sceneValue[0],sceneValue[1]);
  }
}
