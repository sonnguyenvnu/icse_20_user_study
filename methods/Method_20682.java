private void displayNotificationFromProjectActivity(final @NonNull PushNotificationEnvelope envelope){
  final GCM gcm=envelope.gcm();
  final Activity activity=envelope.activity();
  if (activity == null) {
    return;
  }
  final Long projectId=activity.projectId();
  if (projectId == null) {
    return;
  }
  final String projectPhoto=activity.projectPhoto();
  final String projectParam=ObjectUtils.toString(projectId);
  NotificationCompat.Builder notificationBuilder=notificationBuilder(gcm.title(),gcm.alert(),CHANNEL_PROJECT_ACTIVITY).setContentIntent(projectContentIntent(envelope,projectParam));
  if (projectPhoto != null) {
    notificationBuilder=notificationBuilder.setLargeIcon(fetchBitmap(projectPhoto,false));
  }
  final Notification notification=notificationBuilder.build();
  notificationManager().notify(envelope.signature(),notification);
}
