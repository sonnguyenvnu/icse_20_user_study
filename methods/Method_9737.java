/** 
 * Bets 1A0001.
 * @param context the specified context
 */
@RequestProcessing(value="/activity/1A0001/bet",method=HttpMethod.POST) @Before({StopwatchStartAdvice.class,LoginCheck.class,CSRFCheck.class,Activity1A0001Validation.class}) @After(StopwatchEndAdvice.class) public void bet1A0001(final RequestContext context){
  context.renderJSON().renderFalseResult();
  final JSONObject requestJSONObject=(JSONObject)context.attr(Keys.REQUEST);
  final int amount=requestJSONObject.optInt(Common.AMOUNT);
  final int smallOrLarge=requestJSONObject.optInt(Common.SMALL_OR_LARGE);
  final JSONObject currentUser=Sessions.getUser();
  final String fromId=currentUser.optString(Keys.OBJECT_ID);
  final JSONObject ret=activityMgmtService.bet1A0001(fromId,amount,smallOrLarge);
  if (ret.optBoolean(Keys.STATUS_CODE)) {
    String msg=langPropsService.get("activity1A0001BetedLabel");
    final String small=langPropsService.get("activity1A0001BetSmallLabel");
    final String large=langPropsService.get("activity1A0001BetLargeLabel");
    msg=msg.replace("{smallOrLarge}",smallOrLarge == 0 ? small : large);
    msg=msg.replace("{point}",String.valueOf(amount));
    context.renderTrueResult().renderMsg(msg);
  }
}
