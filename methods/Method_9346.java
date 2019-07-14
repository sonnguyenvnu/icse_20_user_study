public void cancel(){
  NotificationCenter.getInstance(currentAccount).postNotificationName(NotificationCenter.recordStopped,0);
}
