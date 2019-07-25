/** 
 * ??????
 * @param cleanSession ??????session???????????????????
 */
public void disconnect(boolean cleanSession){
  if (mClient != null) {
    try {
      mClient.disconnect(cleanSession);
      SharedPreferences sp=gContext.getSharedPreferences("wildfirechat.config",Context.MODE_PRIVATE);
      sp.edit().clear().commit();
    }
 catch (    RemoteException e) {
      e.printStackTrace();
    }
    this.userId=null;
    this.token=null;
  }
}
