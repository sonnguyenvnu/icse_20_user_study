/** 
 * Updates user privacy.
 * @param context the specified context
 */
@RequestProcessing(value="/settings/privacy",method=HttpMethod.POST) @Before({LoginCheck.class,CSRFCheck.class}) public void updatePrivacy(final RequestContext context){
  context.renderJSON();
  final HttpServletRequest request=context.getRequest();
  JSONObject requestJSONObject;
  try {
    requestJSONObject=context.requestJSON();
    request.setAttribute(Keys.REQUEST,requestJSONObject);
  }
 catch (  final Exception e) {
    LOGGER.warn(e.getMessage());
    requestJSONObject=new JSONObject();
  }
  final boolean articleStatus=requestJSONObject.optBoolean(UserExt.USER_ARTICLE_STATUS);
  final boolean commentStatus=requestJSONObject.optBoolean(UserExt.USER_COMMENT_STATUS);
  final boolean followingUserStatus=requestJSONObject.optBoolean(UserExt.USER_FOLLOWING_USER_STATUS);
  final boolean followingTagStatus=requestJSONObject.optBoolean(UserExt.USER_FOLLOWING_TAG_STATUS);
  final boolean followingArticleStatus=requestJSONObject.optBoolean(UserExt.USER_FOLLOWING_ARTICLE_STATUS);
  final boolean watchingArticleStatus=requestJSONObject.optBoolean(UserExt.USER_WATCHING_ARTICLE_STATUS);
  final boolean followerStatus=requestJSONObject.optBoolean(UserExt.USER_FOLLOWER_STATUS);
  final boolean breezemoonStatus=requestJSONObject.optBoolean(UserExt.USER_BREEZEMOON_STATUS);
  final boolean pointStatus=requestJSONObject.optBoolean(UserExt.USER_POINT_STATUS);
  final boolean onlineStatus=requestJSONObject.optBoolean(UserExt.USER_ONLINE_STATUS);
  final boolean uaStatus=requestJSONObject.optBoolean(UserExt.USER_UA_STATUS);
  final boolean userJoinPointRank=requestJSONObject.optBoolean(UserExt.USER_JOIN_POINT_RANK);
  final boolean userJoinUsedPointRank=requestJSONObject.optBoolean(UserExt.USER_JOIN_USED_POINT_RANK);
  JSONObject user=Sessions.getUser();
  final String userId=user.optString(Keys.OBJECT_ID);
  user=userQueryService.getUser(userId);
  user.put(UserExt.USER_ONLINE_STATUS,onlineStatus ? UserExt.USER_XXX_STATUS_C_PUBLIC : UserExt.USER_XXX_STATUS_C_PRIVATE);
  user.put(UserExt.USER_ARTICLE_STATUS,articleStatus ? UserExt.USER_XXX_STATUS_C_PUBLIC : UserExt.USER_XXX_STATUS_C_PRIVATE);
  user.put(UserExt.USER_COMMENT_STATUS,commentStatus ? UserExt.USER_XXX_STATUS_C_PUBLIC : UserExt.USER_XXX_STATUS_C_PRIVATE);
  user.put(UserExt.USER_FOLLOWING_USER_STATUS,followingUserStatus ? UserExt.USER_XXX_STATUS_C_PUBLIC : UserExt.USER_XXX_STATUS_C_PRIVATE);
  user.put(UserExt.USER_FOLLOWING_TAG_STATUS,followingTagStatus ? UserExt.USER_XXX_STATUS_C_PUBLIC : UserExt.USER_XXX_STATUS_C_PRIVATE);
  user.put(UserExt.USER_FOLLOWING_ARTICLE_STATUS,followingArticleStatus ? UserExt.USER_XXX_STATUS_C_PUBLIC : UserExt.USER_XXX_STATUS_C_PRIVATE);
  user.put(UserExt.USER_WATCHING_ARTICLE_STATUS,watchingArticleStatus ? UserExt.USER_XXX_STATUS_C_PUBLIC : UserExt.USER_XXX_STATUS_C_PRIVATE);
  user.put(UserExt.USER_FOLLOWER_STATUS,followerStatus ? UserExt.USER_XXX_STATUS_C_PUBLIC : UserExt.USER_XXX_STATUS_C_PRIVATE);
  user.put(UserExt.USER_BREEZEMOON_STATUS,breezemoonStatus ? UserExt.USER_XXX_STATUS_C_PUBLIC : UserExt.USER_XXX_STATUS_C_PRIVATE);
  user.put(UserExt.USER_POINT_STATUS,pointStatus ? UserExt.USER_XXX_STATUS_C_PUBLIC : UserExt.USER_XXX_STATUS_C_PRIVATE);
  user.put(UserExt.USER_UA_STATUS,uaStatus ? UserExt.USER_XXX_STATUS_C_PUBLIC : UserExt.USER_XXX_STATUS_C_PRIVATE);
  user.put(UserExt.USER_JOIN_POINT_RANK,userJoinPointRank ? UserExt.USER_JOIN_XXX_C_JOIN : UserExt.USER_JOIN_XXX_C_NOT_JOIN);
  user.put(UserExt.USER_JOIN_USED_POINT_RANK,userJoinUsedPointRank ? UserExt.USER_JOIN_XXX_C_JOIN : UserExt.USER_JOIN_XXX_C_NOT_JOIN);
  try {
    userMgmtService.updateUser(user.optString(Keys.OBJECT_ID),user);
    context.renderTrueResult();
  }
 catch (  final ServiceException e) {
    context.renderMsg(e.getMessage());
  }
}
