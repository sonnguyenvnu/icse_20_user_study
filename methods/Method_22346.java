private PendingIntent getSendIntent(@NonNull Context context,@NonNull CoreConfiguration config,@NonNull File reportFile){
  final Intent intent=new Intent(context,NotificationBroadcastReceiver.class);
  intent.setAction(INTENT_ACTION_SEND);
  intent.putExtra(LegacySenderService.EXTRA_ACRA_CONFIG,config);
  intent.putExtra(EXTRA_REPORT_FILE,reportFile);
  return PendingIntent.getBroadcast(context,ACTION_SEND,intent,PendingIntent.FLAG_UPDATE_CURRENT);
}
