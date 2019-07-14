private @NonNull PendingIntent friendFollowActivityIntent(final @NonNull PushNotificationEnvelope envelope){
  final Intent messageThreadIntent=new Intent(this.context,ActivityFeedActivity.class);
  final TaskStackBuilder taskStackBuilder=TaskStackBuilder.create(this.context).addNextIntentWithParentStack(messageThreadIntent);
  return taskStackBuilder.getPendingIntent(envelope.signature(),PendingIntent.FLAG_UPDATE_CURRENT);
}
