private @NonNull PendingIntent projectUpdateContentIntent(final @NonNull PushNotificationEnvelope envelope,final @NonNull Update update,final @NonNull String projectParam){
  final Intent projectIntent=new Intent(this.context,ProjectActivity.class).putExtra(IntentKey.PROJECT_PARAM,projectParam).putExtra(IntentKey.REF_TAG,RefTag.push());
  final Intent updateIntent=new Intent(this.context,UpdateActivity.class).putExtra(IntentKey.PROJECT_PARAM,projectParam).putExtra(IntentKey.UPDATE,update).putExtra(IntentKey.PUSH_NOTIFICATION_ENVELOPE,envelope);
  final TaskStackBuilder taskStackBuilder=TaskStackBuilder.create(this.context).addNextIntentWithParentStack(projectIntent).addNextIntent(updateIntent);
  return taskStackBuilder.getPendingIntent(envelope.signature(),PendingIntent.FLAG_UPDATE_CURRENT);
}
