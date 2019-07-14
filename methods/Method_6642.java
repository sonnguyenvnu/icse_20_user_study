@Override public void didReceivedNotification(int id,int account,Object... args){
  if (id == NotificationCenter.liveLocationsChanged) {
    if (handler != null) {
      handler.post(new Runnable(){
        @Override public void run(){
          ArrayList<LocationController.SharingLocationInfo> infos=getInfos();
          if (infos.isEmpty()) {
            stopSelf();
          }
 else {
            updateNotification(true);
          }
        }
      }
);
    }
  }
}
