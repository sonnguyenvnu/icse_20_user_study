/** 
 * Adds a 'at' type notification with the specified request json object.
 * @param requestJSONObject the specified request json object, for example,"userId"; "", "dataId": ""
 * @throws ServiceException service exception
 */
@Transactional public void addAtNotification(final JSONObject requestJSONObject) throws ServiceException {
  try {
    requestJSONObject.put(Notification.NOTIFICATION_DATA_TYPE,Notification.DATA_TYPE_C_AT);
    addNotification(requestJSONObject);
  }
 catch (  final RepositoryException e) {
    final String msg="Adds a notification [type=at] failed";
    LOGGER.log(Level.ERROR,msg,e);
    throw new ServiceException(msg);
  }
}
