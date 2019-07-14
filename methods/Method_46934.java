/** 
 * Displays a notification, sends intent and cancels progress if there were some failures
 */
void finalizeNotification(ArrayList<HybridFile> failedOps,boolean move){
  if (!move)   getNotificationManager().cancelAll();
  if (failedOps.size() == 0)   return;
  NotificationCompat.Builder mBuilder=new NotificationCompat.Builder(getApplicationContext(),NotificationConstants.CHANNEL_NORMAL_ID);
  mBuilder.setContentTitle(getString(R.string.operationunsuccesful));
  mBuilder.setContentText(getString(R.string.copy_error,getString(getTitle(move)).toLowerCase()));
  mBuilder.setAutoCancel(true);
  getProgressHandler().setCancelled(true);
  Intent intent=new Intent(this,MainActivity.class);
  intent.putExtra(MainActivity.TAG_INTENT_FILTER_FAILED_OPS,failedOps);
  intent.putExtra("move",move);
  PendingIntent pIntent=PendingIntent.getActivity(this,101,intent,PendingIntent.FLAG_UPDATE_CURRENT);
  mBuilder.setContentIntent(pIntent);
  mBuilder.setSmallIcon(R.drawable.ic_folder_lock_open_white_36dp);
  getNotificationManager().notify(NotificationConstants.FAILED_ID,mBuilder.build());
  intent=new Intent(MainActivity.TAG_INTENT_FILTER_GENERAL);
  intent.putExtra(MainActivity.TAG_INTENT_FILTER_FAILED_OPS,failedOps);
  sendBroadcast(intent);
}
