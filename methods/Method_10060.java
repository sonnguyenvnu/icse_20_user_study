/** 
 * Adds a 'reply' type notification with the specified request json object.
 * @param requestJSONObject the specified request json object, for example,"userId"; "", "dataId": ""
 * @throws ServiceException service exception
 */
@Transactional public void addReplyNotification(final JSONObject requestJSONObject) throws ServiceException {
  try {
    requestJSONObject.put(Notification.NOTIFICATION_DATA_TYPE,Notification.DATA_TYPE_C_REPLY);
    addNotification(requestJSONObject);
  }
 catch (  final RepositoryException e) {
    final String msg="Adds a notification [type=reply] failed";
    LOGGER.log(Level.ERROR,msg,e);
    throw new ServiceException(msg);
  }
}
