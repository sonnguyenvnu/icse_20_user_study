/** 
 * Updates username.
 * @param context the specified context
 */
@RequestProcessing(value="/settings/username",method=HttpMethod.POST) @Before({LoginCheck.class}) public void updateUserName(final RequestContext context){
  context.renderJSON();
  final HttpServletRequest request=context.getRequest();
  final JSONObject requestJSONObject=context.requestJSON();
  final JSONObject currentUser=Sessions.getUser();
  final String userId=currentUser.optString(Keys.OBJECT_ID);
  try {
    if (currentUser.optInt(UserExt.USER_POINT) < Pointtransfer.TRANSFER_SUM_C_CHANGE_USERNAME) {
      throw new ServiceException(langPropsService.get("insufficientBalanceLabel"));
    }
    final JSONObject user=userQueryService.getUser(userId);
    final String oldName=user.optString(User.USER_NAME);
    final String newName=requestJSONObject.optString(User.USER_NAME);
    user.put(User.USER_NAME,newName);
    userMgmtService.updateUserName(userId,user);
    pointtransferMgmtService.transfer(userId,Pointtransfer.ID_C_SYS,Pointtransfer.TRANSFER_TYPE_C_CHANGE_USERNAME,Pointtransfer.TRANSFER_SUM_C_CHANGE_USERNAME,oldName + "-" + newName,System.currentTimeMillis(),"");
    context.renderTrueResult();
  }
 catch (  final ServiceException e) {
    context.renderMsg(e.getMessage());
  }
}
