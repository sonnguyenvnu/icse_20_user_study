@Override public boolean performInteraction(@NonNull Context context,@NonNull CoreConfiguration config,@NonNull File reportFile){
  final SharedPreferences prefs=new SharedPreferencesFactory(context,config).create();
  if (prefs.getBoolean(ACRA.PREF_ALWAYS_ACCEPT,false)) {
    return true;
  }
  final NotificationManager notificationManager=((NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE));
  if (notificationManager == null) {
    return true;
  }
  final NotificationConfiguration notificationConfig=ConfigUtils.getPluginConfiguration(config,NotificationConfiguration.class);
  if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
    final NotificationChannel channel=new NotificationChannel(CHANNEL,notificationConfig.channelName(),notificationConfig.resChannelImportance());
    channel.setSound(null,null);
    if (notificationConfig.channelDescription() != null) {
      channel.setDescription(notificationConfig.channelDescription());
    }
    notificationManager.createNotificationChannel(channel);
  }
  final NotificationCompat.Builder notification=new NotificationCompat.Builder(context,CHANNEL).setWhen(System.currentTimeMillis()).setContentTitle(notificationConfig.title()).setContentText(notificationConfig.text()).setSmallIcon(notificationConfig.resIcon()).setPriority(NotificationCompat.PRIORITY_HIGH);
  if (notificationConfig.tickerText() != null) {
    notification.setTicker(notificationConfig.tickerText());
  }
  final PendingIntent sendIntent=getSendIntent(context,config,reportFile);
  final PendingIntent discardIntent=getDiscardIntent(context);
  if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N && notificationConfig.sendWithCommentButtonText() != null) {
    final RemoteInput.Builder remoteInput=new RemoteInput.Builder(KEY_COMMENT);
    if (notificationConfig.commentPrompt() != null) {
      remoteInput.setLabel(notificationConfig.commentPrompt());
    }
    notification.addAction(new NotificationCompat.Action.Builder(notificationConfig.resSendWithCommentButtonIcon(),notificationConfig.sendWithCommentButtonText(),sendIntent).addRemoteInput(remoteInput.build()).build());
  }
  if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
    final RemoteViews bigView=getBigView(context,notificationConfig);
    notification.addAction(notificationConfig.resSendButtonIcon(),notificationConfig.sendButtonText(),sendIntent).addAction(notificationConfig.resDiscardButtonIcon(),notificationConfig.discardButtonText(),discardIntent).setCustomContentView(getSmallView(context,notificationConfig,sendIntent,discardIntent)).setCustomBigContentView(bigView).setCustomHeadsUpContentView(bigView).setStyle(new NotificationCompat.DecoratedCustomViewStyle());
  }
  if (notificationConfig.sendOnClick() || Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN) {
    notification.setContentIntent(sendIntent);
  }
  notification.setDeleteIntent(discardIntent);
  notificationManager.notify(NOTIFICATION_ID,notification.build());
  return false;
}
