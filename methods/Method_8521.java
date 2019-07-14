@Override public void didReceivedNotification(int id,int account,Object... args){
  if (id == NotificationCenter.didUpdateConnectionState) {
    int state=ConnectionsManager.getInstance(currentAccount).getConnectionState();
    if (currentConnectionState != state) {
      currentConnectionState=state;
      updateCurrentConnectionState();
    }
  }
}
