@Override public void handleData(String dataId,UserData userData){
  if (dataId == null) {
    return;
  }
  this.lastUserData=userData;
  printUserData(dataId,userData);
  if (flag != null) {
    flag[0].compareAndSet(false,true);
  }
  if (canNotify()) {
    flag=null;
    composeAndNotify(userData,lastConfigData);
  }
}
