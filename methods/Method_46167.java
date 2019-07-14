@Override public void handleData(String dataId,ConfigData configData){
  if (dataId == null) {
    return;
  }
  this.lastConfigData=configData;
  printConfigData(dataId,configData);
  if (flag != null) {
    flag[1].compareAndSet(false,true);
  }
  if (canNotify()) {
    flag=null;
    composeAndNotify(lastUserData,configData);
  }
}
