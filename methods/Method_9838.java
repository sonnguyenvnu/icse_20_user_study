/** 
 * Updates email.
 * @param context the specified context
 */
@RequestProcessing(value="/settings/email",method=HttpMethod.POST) @Before({LoginCheck.class}) public void updateEmail(final RequestContext context){
  context.renderJSON();
  final HttpServletRequest request=context.getRequest();
  final JSONObject requestJSONObject=context.requestJSON();
  final String captcha=requestJSONObject.optString(CaptchaProcessor.CAPTCHA);
  final JSONObject currentUser=Sessions.getUser();
  final String userId=currentUser.optString(Keys.OBJECT_ID);
  try {
    final JSONObject verifycode=verifycodeQueryService.getVerifycodeByUserId(Verifycode.TYPE_C_EMAIL,Verifycode.BIZ_TYPE_C_BIND_EMAIL,userId);
    if (null == verifycode) {
      final String msg=langPropsService.get("updateFailLabel") + " - " + langPropsService.get("captchaErrorLabel");
      context.renderMsg(msg);
      context.renderJSONValue(Keys.CODE,2);
      return;
    }
    if (!StringUtils.equals(verifycode.optString(Verifycode.CODE),captcha)) {
      final String msg=langPropsService.get("updateFailLabel") + " - " + langPropsService.get("captchaErrorLabel");
      context.renderMsg(msg);
      context.renderJSONValue(Keys.CODE,2);
      return;
    }
    final JSONObject user=userQueryService.getUser(userId);
    final String email=verifycode.optString(Verifycode.RECEIVER);
    user.put(User.USER_EMAIL,email);
    userMgmtService.updateUserEmail(userId,user);
    verifycodeMgmtService.removeByCode(captcha);
    context.renderTrueResult();
  }
 catch (  final ServiceException e) {
    context.renderMsg(e.getMessage());
  }
}
