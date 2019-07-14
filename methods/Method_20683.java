private void displayNotificationFromProjectReminder(final @NonNull PushNotificationEnvelope envelope){
  final GCM gcm=envelope.gcm();
  final PushNotificationEnvelope.Project project=envelope.project();
  if (project == null) {
    return;
  }
  final Notification notification=notificationBuilder(gcm.title(),gcm.alert(),CHANNEL_PROJECT_REMINDER).setContentIntent(projectContentIntent(envelope,ObjectUtils.toString(project.id()))).setLargeIcon(fetchBitmap(project.photo(),false)).build();
  notificationManager().notify(envelope.signature(),notification);
}
