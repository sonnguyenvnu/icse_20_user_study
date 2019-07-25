@Override public RemoteConnectionSettings clone() throws CloneNotSupportedException {
  RemoteConnectionSettings connectionSettings=new RemoteConnectionSettings(getHostName(),getPort());
  connectionSettings.setSuspend(isSuspend());
  return connectionSettings;
}
