@Override public String getAppName(String remoteKey){
  return SocketManager.getInstance().getModuleName(remoteKey);
}
