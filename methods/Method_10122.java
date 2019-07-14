/** 
 * Updates the specified user's username by the given user id.
 * @param userId the given user id
 * @param user   the specified user, contains the new username
 * @throws ServiceException service exception
 */
public void updateUserName(final String userId,final JSONObject user) throws ServiceException {
  final String newUserName=user.optString(User.USER_NAME);
  final Transaction transaction=userRepository.beginTransaction();
  try {
    if (UserRegisterValidation.invalidUserName(newUserName)) {
      throw new ServiceException(langPropsService.get("invalidUserNameLabel") + " [" + newUserName + "]");
    }
    if (!UserExt.NULL_USER_NAME.equals(newUserName) && null != userRepository.getByName(newUserName)) {
      throw new ServiceException(langPropsService.get("duplicatedUserNameLabel") + " [" + newUserName + "]");
    }
    userRepository.update(userId,user,User.USER_NAME);
    transaction.commit();
  }
 catch (  final RepositoryException e) {
    if (transaction.isActive()) {
      transaction.rollback();
    }
    LOGGER.log(Level.ERROR,"Updates username of the user[id=" + userId + "] failed",e);
    throw new ServiceException(e);
  }
}
