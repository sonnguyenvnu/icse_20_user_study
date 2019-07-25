/** 
 * Publish the provided message to an external listener if there is one.
 * @param message the message to publish
 */
private void publish(String message){
  if (notificationPublisher != null) {
    Notification notification=new Notification("JobExecutionApplicationEvent",this,notificationCount++,message);
    notification.setSource(null);
    notificationPublisher.sendNotification(notification);
  }
}
