private PendingIntent getDiscardIntent(@NonNull Context context){
  final Intent intent=new Intent(context,NotificationBroadcastReceiver.class);
  intent.setAction(INTENT_ACTION_DISCARD);
  return PendingIntent.getBroadcast(context,ACTION_DISCARD,intent,PendingIntent.FLAG_UPDATE_CURRENT);
}
