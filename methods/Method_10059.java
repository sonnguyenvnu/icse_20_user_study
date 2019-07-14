/** 
 * Adds a 'following - user' type notification with the specified request json object.
 * @param requestJSONObject the specified request json object, for example,"userId"; "", "dataId": "" // article id
 * @throws ServiceException service exception
 */
@Transactional public void addFollowingUserNotification(final JSONObject requestJSONObject) throws ServiceException {
  try {
    requestJSONObject.put(Notification.NOTIFICATION_DATA_TYPE,Notification.DATA_TYPE_C_FOLLOWING_USER);
    addNotification(requestJSONObject);
  }
 catch (  final RepositoryException e) {
    final String msg="Adds a notification [type=following_user] failed";
    LOGGER.log(Level.ERROR,msg,e);
    throw new ServiceException(msg);
  }
}
