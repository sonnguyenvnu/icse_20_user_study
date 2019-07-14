@TargetApi(26) private String validateChannelId(long dialogId,String name,long[] vibrationPattern,int ledColor,Uri sound,int importance,long[] configVibrationPattern,Uri configSound,int configImportance){
  SharedPreferences preferences=MessagesController.getNotificationsSettings(currentAccount);
  String key="org.telegram.key" + dialogId;
  String channelId=preferences.getString(key,null);
  String settings=preferences.getString(key + "_s",null);
  boolean edited=false;
  StringBuilder newSettings=new StringBuilder();
  String newSettingsHash;
  for (int a=0; a < vibrationPattern.length; a++) {
    newSettings.append(vibrationPattern[a]);
  }
  newSettings.append(ledColor);
  if (sound != null) {
    newSettings.append(sound.toString());
  }
  newSettings.append(importance);
  newSettingsHash=Utilities.MD5(newSettings.toString());
  if (channelId != null && !settings.equals(newSettingsHash)) {
    if (edited) {
      preferences.edit().putString(key,channelId).putString(key + "_s",newSettingsHash).commit();
    }
 else {
      systemNotificationManager.deleteNotificationChannel(channelId);
      channelId=null;
    }
  }
  if (channelId == null) {
    channelId=currentAccount + "channel" + dialogId + "_" + Utilities.random.nextLong();
    NotificationChannel notificationChannel=new NotificationChannel(channelId,name,importance);
    if (ledColor != 0) {
      notificationChannel.enableLights(true);
      notificationChannel.setLightColor(ledColor);
    }
    if (!isEmptyVibration(vibrationPattern)) {
      notificationChannel.enableVibration(true);
      if (vibrationPattern != null && vibrationPattern.length > 0) {
        notificationChannel.setVibrationPattern(vibrationPattern);
      }
    }
 else {
      notificationChannel.enableVibration(false);
    }
    AudioAttributes.Builder builder=new AudioAttributes.Builder();
    builder.setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION);
    builder.setUsage(AudioAttributes.USAGE_NOTIFICATION);
    if (sound != null) {
      notificationChannel.setSound(sound,builder.build());
    }
 else {
      notificationChannel.setSound(null,builder.build());
    }
    systemNotificationManager.createNotificationChannel(notificationChannel);
    preferences.edit().putString(key,channelId).putString(key + "_s",newSettingsHash).commit();
  }
  return channelId;
}
