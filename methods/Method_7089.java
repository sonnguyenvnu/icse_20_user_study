public void setLastOnlineFromOtherDevice(final int time){
  notificationsQueue.postRunnable(() -> {
    if (BuildVars.LOGS_ENABLED) {
      FileLog.d("set last online from other device = " + time);
    }
    lastOnlineFromOtherDevice=time;
  }
);
}
