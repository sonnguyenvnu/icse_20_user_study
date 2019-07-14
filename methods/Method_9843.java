/** 
 * Updates user function.
 * @param context the specified context
 */
@RequestProcessing(value="/settings/function",method=HttpMethod.POST) @Before({LoginCheck.class,CSRFCheck.class}) public void updateFunction(final RequestContext context){
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
  String userListPageSizeStr=requestJSONObject.optString(UserExt.USER_LIST_PAGE_SIZE);
  final int userCommentViewMode=requestJSONObject.optInt(UserExt.USER_COMMENT_VIEW_MODE);
  final int userAvatarViewMode=requestJSONObject.optInt(UserExt.USER_AVATAR_VIEW_MODE);
  final int userListViewMode=requestJSONObject.optInt(UserExt.USER_LIST_VIEW_MODE);
  final boolean notifyStatus=requestJSONObject.optBoolean(UserExt.USER_NOTIFY_STATUS);
  final boolean subMailStatus=requestJSONObject.optBoolean(UserExt.USER_SUB_MAIL_STATUS);
  final boolean keyboardShortcutsStatus=requestJSONObject.optBoolean(UserExt.USER_KEYBOARD_SHORTCUTS_STATUS);
  final boolean userReplyWatchArticleStatus=requestJSONObject.optBoolean(UserExt.USER_REPLY_WATCH_ARTICLE_STATUS);
  final boolean forwardStatus=requestJSONObject.optBoolean(UserExt.USER_FORWARD_PAGE_STATUS);
  String indexRedirectURL=requestJSONObject.optString(UserExt.USER_INDEX_REDIRECT_URL);
  if (!Strings.isURL(indexRedirectURL)) {
    indexRedirectURL="";
  }
  if (StringUtils.isNotBlank(indexRedirectURL) && !StringUtils.startsWith(indexRedirectURL,Latkes.getServePath())) {
    context.renderMsg(langPropsService.get("onlyInternalURLLabel"));
    return;
  }
  if (StringUtils.isNotBlank(indexRedirectURL)) {
    String tmp=StringUtils.substringBefore(indexRedirectURL,"?");
    if (StringUtils.endsWith(tmp,"/")) {
      tmp=StringUtils.substringBeforeLast(tmp,"/");
    }
    if (StringUtils.equalsIgnoreCase(tmp,Latkes.getServePath())) {
      indexRedirectURL="";
    }
  }
  int userListPageSize;
  try {
    userListPageSize=Integer.valueOf(userListPageSizeStr);
    if (10 > userListPageSize) {
      userListPageSize=10;
    }
    if (userListPageSize > 96) {
      userListPageSize=96;
    }
  }
 catch (  final Exception e) {
    userListPageSize=Symphonys.ARTICLE_LIST_CNT;
  }
  JSONObject user=Sessions.getUser();
  final String userId=user.optString(Keys.OBJECT_ID);
  user=userQueryService.getUser(userId);
  user.put(UserExt.USER_LIST_PAGE_SIZE,userListPageSize);
  user.put(UserExt.USER_COMMENT_VIEW_MODE,userCommentViewMode);
  user.put(UserExt.USER_AVATAR_VIEW_MODE,userAvatarViewMode);
  user.put(UserExt.USER_LIST_VIEW_MODE,userListViewMode);
  user.put(UserExt.USER_NOTIFY_STATUS,notifyStatus ? UserExt.USER_XXX_STATUS_C_ENABLED : UserExt.USER_XXX_STATUS_C_DISABLED);
  user.put(UserExt.USER_SUB_MAIL_STATUS,subMailStatus ? UserExt.USER_XXX_STATUS_C_ENABLED : UserExt.USER_XXX_STATUS_C_DISABLED);
  user.put(UserExt.USER_KEYBOARD_SHORTCUTS_STATUS,keyboardShortcutsStatus ? UserExt.USER_XXX_STATUS_C_ENABLED : UserExt.USER_XXX_STATUS_C_DISABLED);
  user.put(UserExt.USER_REPLY_WATCH_ARTICLE_STATUS,userReplyWatchArticleStatus ? UserExt.USER_XXX_STATUS_C_ENABLED : UserExt.USER_XXX_STATUS_C_DISABLED);
  user.put(UserExt.USER_FORWARD_PAGE_STATUS,forwardStatus ? UserExt.USER_XXX_STATUS_C_ENABLED : UserExt.USER_XXX_STATUS_C_DISABLED);
  user.put(UserExt.USER_INDEX_REDIRECT_URL,indexRedirectURL);
  try {
    userMgmtService.updateUser(user.optString(Keys.OBJECT_ID),user);
    context.renderTrueResult();
  }
 catch (  final ServiceException e) {
    context.renderMsg(e.getMessage());
  }
}
