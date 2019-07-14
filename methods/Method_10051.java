/** 
 * Adds a 'invitation link used' type notification with the specified request json object.
 * @param requestJSONObject the specified request json object, for example,"userId"; "", "dataId": "" // invited user id
 * @throws ServiceException service exception
 */
@Transactional public void addInvitationLinkUsedNotification(final JSONObject requestJSONObject) throws ServiceException {
  try {
    requestJSONObject.put(Notification.NOTIFICATION_DATA_TYPE,Notification.DATA_TYPE_C_INVITATION_LINK_USED);
    addNotification(requestJSONObject);
  }
 catch (  final RepositoryException e) {
    final String msg="Adds a notification [type=invitation_link_used] failed";
    LOGGER.log(Level.ERROR,msg,e);
    throw new ServiceException(msg);
  }
}
