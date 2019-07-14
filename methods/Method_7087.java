public void cleanup(){
  popupMessages.clear();
  popupReplyMessages.clear();
  notificationsQueue.postRunnable(() -> {
    opened_dialog_id=0;
    total_unread_count=0;
    personal_count=0;
    pushMessages.clear();
    pushMessagesDict.clear();
    fcmRandomMessagesDict.clear();
    pushDialogs.clear();
    wearNotificationsIds.clear();
    lastWearNotifiedMessageId.clear();
    delayedPushMessages.clear();
    notifyCheck=false;
    lastBadgeCount=0;
    try {
      if (notificationDelayWakelock.isHeld()) {
        notificationDelayWakelock.release();
      }
    }
 catch (    Exception e) {
      FileLog.e(e);
    }
    dismissNotification();
    setBadge(getTotalAllUnreadCount());
    SharedPreferences preferences=MessagesController.getNotificationsSettings(currentAccount);
    SharedPreferences.Editor editor=preferences.edit();
    editor.clear();
    editor.commit();
    if (Build.VERSION.SDK_INT >= 26) {
      try {
        String keyStart=currentAccount + "channel";
        List<NotificationChannel> list=systemNotificationManager.getNotificationChannels();
        int count=list.size();
        for (int a=0; a < count; a++) {
          NotificationChannel channel=list.get(a);
          String id=channel.getId();
          if (id.startsWith(keyStart)) {
            systemNotificationManager.deleteNotificationChannel(id);
          }
        }
      }
 catch (      Throwable e) {
        FileLog.e(e);
      }
    }
  }
);
}
