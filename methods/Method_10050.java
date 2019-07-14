/** 
 * Adds a 'point - perfect article' type notification with the specified request json object.
 * @param requestJSONObject the specified request json object, for example,"userId": "", "dataId": "" // article id
 * @throws ServiceException service exception
 */
@Transactional public void addPerfectArticleNotification(final JSONObject requestJSONObject) throws ServiceException {
  try {
    requestJSONObject.put(Notification.NOTIFICATION_DATA_TYPE,Notification.DATA_TYPE_C_POINT_PERFECT_ARTICLE);
    addNotification(requestJSONObject);
  }
 catch (  final RepositoryException e) {
    final String msg="Adds a notification [type=perfect_article] failed";
    LOGGER.log(Level.ERROR,msg,e);
    throw new ServiceException(msg);
  }
}
