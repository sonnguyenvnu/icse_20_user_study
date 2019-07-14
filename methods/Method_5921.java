/** 
 * Post a notification to be shown in the status bar. If a notification with the same id has already been posted by your application and has not yet been canceled, it will be replaced by the updated information. If  {@code notification} is null, then cancels a previously shownnotification.
 * @param context A {@link Context} to retrieve {@link NotificationManager}.
 * @param id An identifier for this notification unique within your application.
 * @param notification A {@link Notification} object describing what to show the user. If null,then cancels a previously shown notification.
 */
public static void setNotification(Context context,int id,@Nullable Notification notification){
  NotificationManager notificationManager=(NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);
  if (notification != null) {
    notificationManager.notify(id,notification);
  }
 else {
    notificationManager.cancel(id);
  }
}
