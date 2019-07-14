private void displayNotificationFromFriendFollowActivity(final @NonNull PushNotificationEnvelope envelope){
  final GCM gcm=envelope.gcm();
  final Activity activity=envelope.activity();
  if (activity == null) {
    return;
  }
  final Notification notification=notificationBuilder(gcm.title(),gcm.alert(),CHANNEL_FOLLOWING).setLargeIcon(fetchBitmap(activity.userPhoto(),true)).setContentIntent(friendFollowActivityIntent(envelope)).build();
  notificationManager().notify(envelope.signature(),notification);
}
