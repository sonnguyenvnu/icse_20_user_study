private void checkOnline(){
  boolean isOnline=user != null && !user.self && (user.status != null && user.status.expires > ConnectionsManager.getInstance(currentAccount).getCurrentTime() || MessagesController.getInstance(currentAccount).onlinePrivacy.containsKey(user.id));
  onlineProgress=isOnline ? 1.0f : 0.0f;
}
