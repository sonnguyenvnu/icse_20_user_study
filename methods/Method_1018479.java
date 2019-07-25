@Override public void stop(){
  eventBus=null;
  if (refreshTimer != null) {
    refreshTimer.cancel();
  }
}
