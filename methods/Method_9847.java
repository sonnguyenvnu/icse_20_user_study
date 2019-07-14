/** 
 * Queries invitecode state.
 * @param context the specified context
 */
@RequestProcessing(value="/invitecode/state",method=HttpMethod.POST) @Before({LoginCheck.class,CSRFCheck.class}) public void queryInvitecode(final RequestContext context){
  final JSONObject ret=new JSONObject().put(Keys.STATUS_CODE,false);
  context.renderJSON(ret);
  final JSONObject requestJSONObject=context.requestJSON();
  String invitecode=requestJSONObject.optString(Invitecode.INVITECODE);
  if (StringUtils.isBlank(invitecode)) {
    ret.put(Keys.STATUS_CODE,-1);
    ret.put(Keys.MSG,invitecode + " " + langPropsService.get("notFoundInvitecodeLabel"));
    return;
  }
  invitecode=invitecode.trim();
  final JSONObject result=invitecodeQueryService.getInvitecode(invitecode);
  if (null == result) {
    ret.put(Keys.STATUS_CODE,-1);
    ret.put(Keys.MSG,langPropsService.get("notFoundInvitecodeLabel"));
  }
 else {
    final int status=result.optInt(Invitecode.STATUS);
    ret.put(Keys.STATUS_CODE,status);
switch (status) {
case Invitecode.STATUS_C_USED:
      ret.put(Keys.MSG,langPropsService.get("invitecodeUsedLabel"));
    break;
case Invitecode.STATUS_C_UNUSED:
  String msg=langPropsService.get("invitecodeOkLabel");
msg=msg.replace("${time}",DateFormatUtils.format(result.optLong(Keys.OBJECT_ID) + Symphonys.INVITECODE_EXPIRED,"yyyy-MM-dd HH:mm"));
ret.put(Keys.MSG,msg);
break;
case Invitecode.STATUS_C_STOPUSE:
ret.put(Keys.MSG,langPropsService.get("invitecodeStopLabel"));
break;
default :
ret.put(Keys.MSG,langPropsService.get("notFoundInvitecodeLabel"));
}
}
}
