/** 
 * Updates a user's profiles by the specified request json object.
 * @param requestJSONObject the specified request json object (user), for example,"oId": "", "userNickname": "", "userTags": "", "userURL": "", "userQQ": "", "userIntro": "", "userAvatarType": int, "userAvatarURL": "", "userCommentViewMode": int
 * @throws ServiceException service exception
 */
public void updateProfiles(final JSONObject requestJSONObject) throws ServiceException {
  final Transaction transaction=userRepository.beginTransaction();
  try {
    final String oldUserId=requestJSONObject.optString(Keys.OBJECT_ID);
    final JSONObject oldUser=userRepository.get(oldUserId);
    if (null == oldUser) {
      throw new ServiceException(langPropsService.get("updateFailLabel"));
    }
    final String userTags=requestJSONObject.optString(UserExt.USER_TAGS);
    oldUser.put(UserExt.USER_TAGS,userTags);
    tag(oldUser);
    oldUser.put(UserExt.USER_NICKNAME,requestJSONObject.optString(UserExt.USER_NICKNAME));
    oldUser.put(User.USER_URL,requestJSONObject.optString(User.USER_URL));
    oldUser.put(UserExt.USER_QQ,requestJSONObject.optString(UserExt.USER_QQ));
    oldUser.put(UserExt.USER_INTRO,requestJSONObject.optString(UserExt.USER_INTRO));
    oldUser.put(UserExt.USER_AVATAR_TYPE,requestJSONObject.optString(UserExt.USER_AVATAR_TYPE));
    oldUser.put(UserExt.USER_AVATAR_URL,requestJSONObject.optString(UserExt.USER_AVATAR_URL));
    oldUser.put(UserExt.USER_COMMENT_VIEW_MODE,requestJSONObject.optInt(UserExt.USER_COMMENT_VIEW_MODE));
    oldUser.put(UserExt.USER_UPDATE_TIME,System.currentTimeMillis());
    userRepository.update(oldUserId,oldUser);
    transaction.commit();
  }
 catch (  final RepositoryException e) {
    if (transaction.isActive()) {
      transaction.rollback();
    }
    LOGGER.log(Level.ERROR,"Updates user profiles failed",e);
    throw new ServiceException(langPropsService.get("updateFailLabel"));
  }
}
