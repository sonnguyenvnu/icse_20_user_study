/** 
 * Initializes the  {@link UploadTask}.<br> Override this method in subclasses to perform custom task initialization and to get the custom parameters set in  {@link UploadRequest#initializeIntent(Intent)} method.
 * @param service Upload Service instance. You should use this reference as your context.
 * @param intent  intent sent to the service to start the upload
 * @throws IOException if an I/O exception occurs while initializing
 */
protected void init(UploadService service,Intent intent) throws IOException {
  this.notificationManager=(NotificationManager)service.getSystemService(Context.NOTIFICATION_SERVICE);
  this.params=intent.getParcelableExtra(UploadService.PARAM_TASK_PARAMETERS);
  this.service=service;
  this.mainThreadHandler=new Handler(service.getMainLooper());
  if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O && params.notificationConfig != null) {
    String notificationChannelId=params.notificationConfig.getNotificationChannelId();
    if (notificationChannelId == null) {
      params.notificationConfig.setNotificationChannelId(UploadService.NAMESPACE);
      notificationChannelId=UploadService.NAMESPACE;
    }
    if (notificationManager.getNotificationChannel(notificationChannelId) == null) {
      NotificationChannel channel=new NotificationChannel(notificationChannelId,"Upload Service channel",NotificationManager.IMPORTANCE_LOW);
      if (!params.notificationConfig.isRingToneEnabled()) {
        channel.setSound(null,null);
      }
      notificationManager.createNotificationChannel(channel);
    }
  }
}
