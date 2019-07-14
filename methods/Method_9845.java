/** 
 * Updates user avatar.
 * @param context the specified context
 */
@RequestProcessing(value="/settings/avatar",method=HttpMethod.POST) @Before({LoginCheck.class,CSRFCheck.class,UpdateProfilesValidation.class}) public void updateAvatar(final RequestContext context){
  context.renderJSON();
  final HttpServletRequest request=context.getRequest();
  final JSONObject requestJSONObject=(JSONObject)context.attr(Keys.REQUEST);
  final String userAvatarURL=requestJSONObject.optString(UserExt.USER_AVATAR_URL);
  JSONObject user=Sessions.getUser();
  final String userId=user.optString(Keys.OBJECT_ID);
  user=userQueryService.getUser(userId);
  user.put(UserExt.USER_AVATAR_TYPE,UserExt.USER_AVATAR_TYPE_C_UPLOAD);
  user.put(UserExt.USER_UPDATE_TIME,System.currentTimeMillis());
  if (Strings.contains(userAvatarURL,new String[]{"<",">","\"","'"})) {
    user.put(UserExt.USER_AVATAR_URL,AvatarQueryService.DEFAULT_AVATAR_URL);
  }
 else {
    if (Symphonys.QN_ENABLED) {
      final String qiniuDomain=Symphonys.UPLOAD_QINIU_DOMAIN;
      if (!StringUtils.startsWith(userAvatarURL,qiniuDomain)) {
        user.put(UserExt.USER_AVATAR_URL,AvatarQueryService.DEFAULT_AVATAR_URL);
      }
 else {
        user.put(UserExt.USER_AVATAR_URL,userAvatarURL);
      }
    }
 else {
      user.put(UserExt.USER_AVATAR_URL,userAvatarURL);
    }
  }
  try {
    userMgmtService.updateUser(user.optString(Keys.OBJECT_ID),user);
    context.renderTrueResult();
  }
 catch (  final ServiceException e) {
    context.renderMsg(e.getMessage());
  }
}
