/** 
 * Updates a user.
 * @param context the specified context
 */
@RequestProcessing(value="/admin/user/{userId}",method=HttpMethod.POST) @Before({StopwatchStartAdvice.class,PermissionCheck.class}) @After({PermissionGrant.class,StopwatchEndAdvice.class}) public void updateUser(final RequestContext context){
  final String userId=context.pathVar("userId");
  final HttpServletRequest request=context.getRequest();
  final AbstractFreeMarkerRenderer renderer=new SkinRenderer(context,"admin/user.ftl");
  final Map<String,Object> dataModel=renderer.getDataModel();
  final JSONObject user=userQueryService.getUser(userId);
  dataModel.put(User.USER,user);
  final String oldRole=user.optString(User.USER_ROLE);
  final JSONObject result=roleQueryService.getRoles(1,Integer.MAX_VALUE,10);
  final List<JSONObject> roles=(List<JSONObject>)result.opt(Role.ROLES);
  dataModel.put(Role.ROLES,roles);
  final Enumeration<String> parameterNames=request.getParameterNames();
  while (parameterNames.hasMoreElements()) {
    final String name=parameterNames.nextElement();
    final String value=context.param(name);
switch (name) {
case UserExt.USER_POINT:
case UserExt.USER_APP_ROLE:
case UserExt.USER_STATUS:
case UserExt.USER_COMMENT_VIEW_MODE:
case UserExt.USER_AVATAR_VIEW_MODE:
case UserExt.USER_LIST_PAGE_SIZE:
case UserExt.USER_LIST_VIEW_MODE:
case UserExt.USER_NOTIFY_STATUS:
case UserExt.USER_SUB_MAIL_STATUS:
case UserExt.USER_KEYBOARD_SHORTCUTS_STATUS:
case UserExt.USER_REPLY_WATCH_ARTICLE_STATUS:
case UserExt.USER_GEO_STATUS:
case UserExt.USER_ARTICLE_STATUS:
case UserExt.USER_COMMENT_STATUS:
case UserExt.USER_FOLLOWING_USER_STATUS:
case UserExt.USER_FOLLOWING_TAG_STATUS:
case UserExt.USER_FOLLOWING_ARTICLE_STATUS:
case UserExt.USER_WATCHING_ARTICLE_STATUS:
case UserExt.USER_BREEZEMOON_STATUS:
case UserExt.USER_FOLLOWER_STATUS:
case UserExt.USER_POINT_STATUS:
case UserExt.USER_ONLINE_STATUS:
case UserExt.USER_UA_STATUS:
case UserExt.USER_JOIN_POINT_RANK:
case UserExt.USER_JOIN_USED_POINT_RANK:
case UserExt.USER_FORWARD_PAGE_STATUS:
      user.put(name,Integer.valueOf(value));
    break;
case User.USER_PASSWORD:
  final String oldPwd=user.getString(name);
if (!oldPwd.equals(value) && StringUtils.isNotBlank(value)) {
  user.put(name,DigestUtils.md5Hex(value));
}
break;
default :
user.put(name,value);
break;
}
}
final JSONObject currentUser=Sessions.getUser();
if (!Role.ROLE_ID_C_ADMIN.equals(currentUser.optString(User.USER_ROLE))) {
user.put(User.USER_ROLE,oldRole);
}
try {
userMgmtService.updateUser(userId,user);
operationMgmtService.addOperation(Operation.newOperation(request,Operation.OPERATION_CODE_C_UPDATE_USER,userId));
}
 catch (final Exception e) {
LOGGER.log(Level.ERROR,"Updates a user failed",e);
return;
}
dataModelService.fillHeaderAndFooter(context,dataModel);
}
