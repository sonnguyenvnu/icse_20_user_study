/** 
 * Adds a 'report handled' type notification with the specified request json object.
 * @param requestJSONObject the specified request json object, for example,"userId"; "", "dataId": "" // report handled point transfer id
 * @throws ServiceException service exception
 */
@Transactional public void addReportHandledNotification(final JSONObject requestJSONObject) throws ServiceException {
  try {
    requestJSONObject.put(Notification.NOTIFICATION_DATA_TYPE,Notification.DATA_TYPE_C_POINT_REPORT_HANDLED);
    addNotification(requestJSONObject);
  }
 catch (  final RepositoryException e) {
    final String msg="Adds a notification [type=report_handled] failed";
    LOGGER.log(Level.ERROR,msg,e);
    throw new ServiceException(msg);
  }
}
