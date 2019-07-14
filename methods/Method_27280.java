@Override public void onMessageReceived(RemoteMessage remoteMessage){
  super.onMessageReceived(remoteMessage);
  if (remoteMessage != null) {
    if (remoteMessage.getData() != null && !remoteMessage.getData().isEmpty()) {
      Date date=new Date(remoteMessage.getSentTime());
      FastHubNotification fastHubNotification=RestProvider.gson.fromJson(new JSONObject(remoteMessage.getData()).toString(),FastHubNotification.class);
      fastHubNotification.setDate(date);
      FastHubNotification.save(fastHubNotification);
    }
 else     if (remoteMessage.getNotification() != null) {
      String title=remoteMessage.getNotification().getTitle();
      String body=remoteMessage.getNotification().getBody();
      if (remoteMessage.getData() != null && !remoteMessage.getData().isEmpty()) {
        title=title == null ? remoteMessage.getData().get("title") : title;
        body=body == null ? remoteMessage.getData().get("message") : body;
      }
      Intent intent=new Intent(this,MainActivity.class);
      intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
      PendingIntent pendingIntent=PendingIntent.getActivity(this,0,intent,PendingIntent.FLAG_ONE_SHOT);
      NotificationCompat.Builder notificationBuilder=new NotificationCompat.Builder(this,"In App-Notifications").setSmallIcon(R.drawable.ic_notification).setContentTitle(title).setContentText(body).setAutoCancel(true).setContentIntent(pendingIntent);
      NotificationManager notificationManager=(NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
      if (notificationManager != null) {
        notificationManager.notify(1,notificationBuilder.build());
      }
    }
  }
}
