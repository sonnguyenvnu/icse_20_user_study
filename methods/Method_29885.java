private void notifyError(ApiError error){
  Context context=getContext();
  String contentTitle=context.getString(R.string.broadcast_send_failed_notification_title_format,ApiError.getErrorString(error,context));
  String contentText=context.getString(R.string.broadcast_send_failed_notification_text);
  SendBroadcastFragment.LinkInfo linkInfo;
  if (!TextUtils.isEmpty(mLinkUrl)) {
    linkInfo=new SendBroadcastFragment.LinkInfo(mLinkUrl,mLinkTitle,null,null);
  }
 else {
    linkInfo=null;
  }
  Intent intent=SendBroadcastActivity.makeIntent(mText,mImageUris,linkInfo,context);
  int requestCode=ObjectsCompat.hash(mText,mImageUris,mLinkTitle,mLinkUrl);
  PendingIntent pendingIntent=PendingIntent.getActivity(context,requestCode,intent,PendingIntent.FLAG_UPDATE_CURRENT);
  Notification notification=createNotificationBuilder(contentTitle,contentText).setContentIntent(pendingIntent).setAutoCancel(true).build();
  NotificationManagerCompat.from(context).notify(Notifications.Ids.SEND_BROADCAST_FAILED,notification);
}
