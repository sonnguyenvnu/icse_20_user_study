/** 
 * Updates the specified user by the given user id.
 * @param userId the given user id
 * @param user   the specified user
 * @throws ServiceException service exception
 */
public void updateUser(final String userId,final JSONObject user) throws ServiceException {
  final Transaction transaction=userRepository.beginTransaction();
  try {
    final JSONObject old=userRepository.get(userId);
    final String oldRoleId=old.optString(User.USER_ROLE);
    final String newRoleId=user.optString(User.USER_ROLE);
    userRepository.update(userId,user);
    transaction.commit();
    if (!oldRoleId.equals(newRoleId)) {
      final JSONObject notification=new JSONObject();
      notification.put(Notification.NOTIFICATION_USER_ID,userId);
      notification.put(Notification.NOTIFICATION_DATA_ID,oldRoleId + "-" + newRoleId);
      notificationMgmtService.addSysAnnounceRoleChangedNotification(notification);
    }
  }
 catch (  final RepositoryException e) {
    if (transaction.isActive()) {
      transaction.rollback();
    }
    LOGGER.log(Level.ERROR,"Updates a user[id=" + userId + "] failed",e);
    throw new ServiceException(e);
  }
}
