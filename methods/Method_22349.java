@NonNull private RemoteViews getBigView(@NonNull Context context,@NonNull NotificationConfiguration notificationConfig){
  final RemoteViews view=new RemoteViews(context.getPackageName(),R.layout.notification_big);
  view.setTextViewText(R.id.text,notificationConfig.text());
  view.setTextViewText(R.id.title,notificationConfig.title());
  return view;
}
