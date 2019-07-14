/** 
 * Resets password.
 * @param context the specified context
 */
@RequestProcessing(value="/reset-pwd",method=HttpMethod.POST) public void resetPwd(final RequestContext context){
  context.renderJSON();
  final HttpServletResponse response=context.getResponse();
  final JSONObject requestJSONObject=context.requestJSON();
  final String password=requestJSONObject.optString(User.USER_PASSWORD);
  final String userId=requestJSONObject.optString(UserExt.USER_T_ID);
  final String code=requestJSONObject.optString(Keys.CODE);
  final JSONObject verifycode=verifycodeQueryService.getVerifycode(code);
  if (null == verifycode || !verifycode.optString(Verifycode.USER_ID).equals(userId)) {
    context.renderMsg(langPropsService.get("verifycodeExpiredLabel"));
    return;
  }
  String name=null;
  String email=null;
  try {
    final JSONObject user=userQueryService.getUser(userId);
    if (null == user || UserExt.USER_STATUS_C_VALID != user.optInt(UserExt.USER_STATUS)) {
      context.renderMsg(langPropsService.get("resetPwdLabel") + " - " + "User Not Found");
      return;
    }
    user.put(User.USER_PASSWORD,password);
    userMgmtService.updatePassword(user);
    verifycodeMgmtService.removeByCode(code);
    context.renderTrueResult();
    LOGGER.info("User [email=" + user.optString(User.USER_EMAIL) + "] reseted password");
    Sessions.login(response,userId,true);
  }
 catch (  final ServiceException e) {
    final String msg=langPropsService.get("resetPwdLabel") + " - " + e.getMessage();
    LOGGER.log(Level.ERROR,msg + "[name={0}, email={1}]",name,email);
    context.renderMsg(msg);
  }
}
