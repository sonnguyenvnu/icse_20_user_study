private void displayNotificationFromMessageActivity(final @NonNull PushNotificationEnvelope envelope,final @NonNull MessageThread messageThread){
  final GCM gcm=envelope.gcm();
  final PushNotificationEnvelope.Message message=envelope.message();
  if (message == null) {
    return;
  }
  final Notification notification=notificationBuilder(gcm.title(),gcm.alert(),CHANNEL_MESSAGES).setContentIntent(messageThreadIntent(envelope,messageThread)).build();
  notificationManager().notify(envelope.signature(),notification);
}
