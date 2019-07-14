private @NonNull PendingIntent surveyResponseContentIntent(final @NonNull PushNotificationEnvelope envelope,final @NonNull SurveyResponse surveyResponse){
  final Intent activityFeedIntent=new Intent(this.context,ActivityFeedActivity.class);
  final Intent surveyResponseIntent=new Intent(this.context,SurveyResponseActivity.class).putExtra(IntentKey.SURVEY_RESPONSE,surveyResponse);
  final TaskStackBuilder taskStackBuilder=TaskStackBuilder.create(this.context).addNextIntentWithParentStack(activityFeedIntent).addNextIntent(surveyResponseIntent);
  return taskStackBuilder.getPendingIntent(envelope.signature(),PendingIntent.FLAG_UPDATE_CURRENT);
}
