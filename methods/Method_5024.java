@Override public void onDestroy(){
  logd("onDestroy");
  DownloadManagerHelper downloadManagerHelper=downloadManagerListeners.get(getClass());
  boolean unschedule=downloadManager.getDownloadCount() <= 0;
  downloadManagerHelper.detachService(this,unschedule);
  if (foregroundNotificationUpdater != null) {
    foregroundNotificationUpdater.stopPeriodicUpdates();
  }
}
