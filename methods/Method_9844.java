/** 
 * Updates user profiles.
 * @param context the specified context
 */
@RequestProcessing(value="/settings/profiles",method=HttpMethod.POST) @Before({LoginCheck.class,CSRFCheck.class,UpdateProfilesValidation.class}) public void updateProfiles(final RequestContext context){
  context.renderJSON();
  final JSONObject requestJSONObject=(JSONObject)context.attr(Keys.REQUEST);
  final String userTags=requestJSONObject.optString(UserExt.USER_TAGS);
  final String userURL=requestJSONObject.optString(User.USER_URL);
  final String userQQ=requestJSONObject.optString(UserExt.USER_QQ);
  String userIntro=StringUtils.trim(requestJSONObject.optString(UserExt.USER_INTRO));
  userIntro=Escapes.escapeHTML(userIntro);
  String userNickname=StringUtils.trim(requestJSONObject.optString(UserExt.USER_NICKNAME));
  userNickname=Escapes.escapeHTML(userNickname);
  final JSONObject user=Sessions.getUser();
  user.put(UserExt.USER_TAGS,userTags);
  user.put(User.USER_URL,userURL);
  user.put(UserExt.USER_QQ,userQQ);
  user.put(UserExt.USER_INTRO,userIntro);
  user.put(UserExt.USER_NICKNAME,userNickname);
  user.put(UserExt.USER_AVATAR_TYPE,UserExt.USER_AVATAR_TYPE_C_UPLOAD);
  try {
    userMgmtService.updateProfiles(user);
    context.renderTrueResult();
  }
 catch (  final ServiceException e) {
    context.renderMsg(e.getMessage());
  }
}
