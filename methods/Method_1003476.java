public NotificationsHelper show(long id){
  Notification mNotification=mBuilder.build();
  if (mNotification.contentIntent == null) {
    mBuilder.setContentIntent(PendingIntent.getActivity(mContext,0,new Intent(),PendingIntent.FLAG_UPDATE_CURRENT));
  }
  mNotificationManager.notify(String.valueOf(id),0,mBuilder.build());
  return this;
}
