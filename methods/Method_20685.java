private @NonNull PendingIntent messageThreadIntent(final @NonNull PushNotificationEnvelope envelope,final @NonNull MessageThread messageThread){
  final Intent messageThreadIntent=new Intent(this.context,MessagesActivity.class).putExtra(IntentKey.MESSAGE_THREAD,messageThread).putExtra(IntentKey.KOALA_CONTEXT,KoalaContext.Message.PUSH);
  final TaskStackBuilder taskStackBuilder=TaskStackBuilder.create(this.context).addNextIntentWithParentStack(messageThreadIntent);
  return taskStackBuilder.getPendingIntent(envelope.signature(),PendingIntent.FLAG_UPDATE_CURRENT);
}
