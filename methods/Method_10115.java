/** 
 * Deactivates the specified user.
 * @param userId the specified user id
 * @throws ServiceException service exception
 */
public void deactivateUser(final String userId) throws ServiceException {
  final Transaction transaction=userRepository.beginTransaction();
  try {
    final JSONObject user=userRepository.get(userId);
    final String userNo=user.optString(UserExt.USER_NO);
    final String newName=UserExt.ANONYMOUS_USER_NAME + userNo;
    user.put(User.USER_NAME,newName);
    user.put(User.USER_EMAIL,newName + UserExt.USER_BUILTIN_EMAIL_SUFFIX);
    user.put(User.USER_PASSWORD,DigestUtils.md5Hex(RandomStringUtils.randomAlphanumeric(8)));
    user.put(UserExt.USER_NICKNAME,"");
    user.put(UserExt.USER_TAGS,"");
    user.put(User.USER_URL,"");
    user.put(UserExt.USER_INTRO,"");
    user.put(UserExt.USER_AVATAR_URL,AvatarQueryService.DEFAULT_AVATAR_URL);
    user.put(UserExt.USER_CITY,"");
    user.put(UserExt.USER_PROVINCE,"");
    user.put(UserExt.USER_COUNTRY,"");
    user.put(User.USER_ROLE,Role.ROLE_ID_C_DEFAULT);
    user.put(UserExt.USER_ONLINE_FLAG,false);
    user.put(UserExt.USER_STATUS,UserExt.USER_STATUS_C_DEACTIVATED);
    userRepository.update(userId,user);
    notificationRepository.removeByUserId(userId);
    livenessRepository.removeByUserId(userId);
    visitRepository.removeByUserId(userId);
    emotionRepository.removeByUserId(userId);
    transaction.commit();
  }
 catch (  final RepositoryException e) {
    if (transaction.isActive()) {
      transaction.rollback();
    }
    LOGGER.log(Level.ERROR,"Deactivates a user [id=" + userId + "] failed",e);
    throw new ServiceException(e);
  }
}
