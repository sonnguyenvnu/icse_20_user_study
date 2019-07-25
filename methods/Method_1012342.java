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
public void notify(String tag,String groupId,boolean isAllowVibrate,boolean isAllowVoice,Context context,String title,int icon,CharSequence content,Class<? extends Activity> targetClass){
  NotificationCompat.Builder builder=new NotificationCompat.Builder(context);
  builder.setSmallIcon(icon);
  builder.setContentText(content);
  builder.setContentTitle(title);
  builder.setTicker(title);
  builder.setAutoCancel(true);
  if (isAllowVibrate) {
    builder.setDefaults(Notification.DEFAULT_VIBRATE);
  }
  if (isAllowVoice) {
    builder.setDefaults(Notification.DEFAULT_SOUND);
  }
  Intent intent;
  if (targetClass != null) {
    intent=new Intent(context,targetClass);
  }
 else {
    intent=new Intent("custom.activity.action.main");
  }
  intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
  intent.putExtra(ConstantUtil.NOTIFICATION_TAG,tag);
  if (groupId != null) {
    intent.putExtra(ConstantUtil.GROUP_ID,groupId);
  }
  PendingIntent pendingIntent=PendingIntent.getActivity(context,0,intent,PendingIntent.FLAG_UPDATE_CURRENT);
  builder.setContentIntent(pendingIntent);
  sNotificationManager.notify(ConstantUtil.NOTIFY_ID,builder.build());
}
