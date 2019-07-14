protected void initializeAccountRelatedThings(){
  updateServerConfig();
  NotificationCenter.getInstance(currentAccount).addObserver(this,NotificationCenter.appDidLogout);
  ConnectionsManager.getInstance(currentAccount).setAppPaused(false,false);
  controller=createController();
  controller.setConnectionStateListener(this);
}
