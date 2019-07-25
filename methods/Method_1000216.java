public synchronized void init(MusicService service){
  this.service=service;
  notificationManager=(NotificationManager)service.getSystemService(NOTIFICATION_SERVICE);
  if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
    createNotificationChannel();
  }
}
