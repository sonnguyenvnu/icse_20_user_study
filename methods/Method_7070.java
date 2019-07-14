@Override public void didReceivedNotification(int id,int account,Object... args){
  updatePlaybackState(null);
  handlePlayRequest();
}
