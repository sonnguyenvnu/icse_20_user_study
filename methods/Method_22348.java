@NonNull private RemoteViews getSmallView(@NonNull Context context,@NonNull NotificationConfiguration notificationConfig,@NonNull PendingIntent sendIntent,@NonNull PendingIntent discardIntent){
  final RemoteViews view=new RemoteViews(context.getPackageName(),R.layout.notification_small);
  view.setTextViewText(R.id.text,notificationConfig.text());
  view.setTextViewText(R.id.title,notificationConfig.title());
  view.setImageViewResource(R.id.button_send,notificationConfig.resSendButtonIcon());
  view.setImageViewResource(R.id.button_discard,notificationConfig.resDiscardButtonIcon());
  view.setOnClickPendingIntent(R.id.button_send,sendIntent);
  view.setOnClickPendingIntent(R.id.button_discard,discardIntent);
  return view;
}
