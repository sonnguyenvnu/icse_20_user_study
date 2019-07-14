public static boolean exists(long notificationId){
  return App.getInstance().getDataStore().toBlocking().select(NotificationQueue.class).where(NotificationQueue.NOTIFICATION_ID.eq(notificationId)).get().firstOrNull() != null;
}
