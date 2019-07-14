/** 
 * Updates a user's password by the specified request json object.
 * @param requestJSONObject the specified request json object (user), for example,"oId": "", "userPassword": "", // Hashed
 * @throws ServiceException service exception
 */
public void updatePassword(final JSONObject requestJSONObject) throws ServiceException {
  final Transaction transaction=userRepository.beginTransaction();
  try {
    final String oldUserId=requestJSONObject.optString(Keys.OBJECT_ID);
    final JSONObject oldUser=userRepository.get(oldUserId);
    if (null == oldUser) {
      throw new ServiceException(langPropsService.get("updateFailLabel"));
    }
    oldUser.put(User.USER_PASSWORD,requestJSONObject.optString(User.USER_PASSWORD));
    userRepository.update(oldUserId,oldUser,User.USER_PASSWORD);
    transaction.commit();
  }
 catch (  final RepositoryException e) {
    if (transaction.isActive()) {
      transaction.rollback();
    }
    LOGGER.log(Level.ERROR,"Updates user password failed",e);
    throw new ServiceException(e);
  }
}
