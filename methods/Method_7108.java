private void dismissNotification(){
  try {
    notificationManager.cancel(notificationId);
    pushMessages.clear();
    pushMessagesDict.clear();
    lastWearNotifiedMessageId.clear();
    for (int a=0; a < wearNotificationsIds.size(); a++) {
      notificationManager.cancel(wearNotificationsIds.valueAt(a));
    }
    wearNotificationsIds.clear();
    AndroidUtilities.runOnUIThread(() -> NotificationCenter.getGlobalInstance().postNotificationName(NotificationCenter.pushMessagesUpdated));
    if (WearDataLayerListenerService.isWatchConnected()) {
      try {
        JSONObject o=new JSONObject();
        o.put("id",UserConfig.getInstance(currentAccount).getClientUserId());
        o.put("cancel_all",true);
        WearDataLayerListenerService.sendMessageToWatch("/notify",o.toString().getBytes(),"remote_notifications");
      }
 catch (      JSONException ignore) {
      }
    }
  }
 catch (  Exception e) {
    FileLog.e(e);
  }
}
