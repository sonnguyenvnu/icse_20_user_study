private android.app.Notification getSummaryGroupNotification(@NonNull Notification thread,int accentColor,boolean toNotificationActivity){
  PendingIntent pendingIntent=PendingIntent.getActivity(getApplicationContext(),0,new Intent(getApplicationContext(),NotificationActivity.class),PendingIntent.FLAG_UPDATE_CURRENT);
  NotificationCompat.Builder builder=getNotification(thread.getSubject().getTitle(),thread.getRepository().getFullName(),thread.getRepository() != null ? thread.getRepository().getFullName() : "general").setContentIntent(toNotificationActivity ? pendingIntent : getPendingIntent(thread.getId(),thread.getSubject().getUrl())).addAction(R.drawable.ic_github,getString(R.string.open),getPendingIntent(thread.getId(),thread.getSubject().getUrl())).addAction(R.drawable.ic_eye_off,getString(R.string.mark_as_read),getReadOnlyPendingIntent(thread.getId(),thread.getSubject().getUrl())).setWhen(thread.getUpdatedAt() != null ? thread.getUpdatedAt().getTime() : System.currentTimeMillis()).setShowWhen(true).setSmallIcon(R.drawable.ic_notification).setColor(accentColor).setGroup(NOTIFICATION_GROUP_ID).setGroupSummary(true);
  if (PrefGetter.isNotificationSoundEnabled()) {
    builder.setDefaults(NotificationCompat.DEFAULT_ALL).setSound(PrefGetter.getNotificationSound(),AudioManager.STREAM_NOTIFICATION);
  }
  return builder.build();
}
