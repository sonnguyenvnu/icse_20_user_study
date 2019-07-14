/** 
 * Makes the specified notification have been read.
 * @param notification the specified notification, return directly if this notification has been read(notification.hasRead equals to  {@code true})
 */
@Transactional public void makeRead(final JSONObject notification){
  if (notification.optBoolean(Notification.NOTIFICATION_HAS_READ)) {
    return;
  }
  final String id=notification.optString(Keys.OBJECT_ID);
  try {
    final JSONObject record=notificationRepository.get(id);
    if (null == record) {
      return;
    }
    record.put(Notification.NOTIFICATION_HAS_READ,true);
    notificationRepository.update(id,record);
  }
 catch (  final RepositoryException e) {
    final String msg="Makes notification as read failed";
    LOGGER.log(Level.ERROR,msg,e);
  }
}
