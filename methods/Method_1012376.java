/** 
 * ????????
 * @param isAllowVibrate ??????
 * @param isAllowVoice   ??????
 * @param context        context
 * @param title          ??
 * @param icon           ??
 * @param content        ??
 * @param targetClass    ??Activity
 */
public void notify(boolean isAllowVibrate,boolean isAllowVoice,Context context,String title,int icon,CharSequence content,Class<? extends Activity> targetClass){
  NotificationCompat.Builder builder=new NotificationCompat.Builder(context);
  builder.setSmallIcon(icon);
  builder.setContentText(content);
  builder.setContentTitle(title);
  builder.setTicker(title);
  builder.setAutoCancel(true);
  if (isAllowVoice) {
    builder.setDefaults(Notification.DEFAULT_SOUND);
  }
  if (isAllowVibrate) {
    builder.setDefaults(Notification.DEFAULT_VIBRATE);
  }
  if (targetClass != null) {
    Intent intent=new Intent(context,targetClass);
    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
    PendingIntent pendingIntent=PendingIntent.getActivity(context,0,intent,PendingIntent.FLAG_UPDATE_CURRENT);
    builder.setContentIntent(pendingIntent);
  }
  mManager.notify(1,builder.build());
}
