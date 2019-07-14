/** 
 * Starts gobang.
 * @param context the specified context
 */
@RequestProcessing(value="/activity/gobang/start",method=HttpMethod.POST) @Before({StopwatchStartAdvice.class,LoginCheck.class}) @After(StopwatchEndAdvice.class) public void startGobang(final RequestContext context){
  final JSONObject ret=new JSONObject().put(Keys.STATUS_CODE,false);
  final JSONObject currentUser=Sessions.getUser();
  final boolean succ=currentUser.optInt(UserExt.USER_POINT) - Pointtransfer.TRANSFER_SUM_C_ACTIVITY_GOBANG_START >= 0;
  ret.put(Keys.STATUS_CODE,succ);
  final String msg=succ ? "started" : langPropsService.get("activityStartGobangFailLabel");
  ret.put(Keys.MSG,msg);
  context.renderJSON(ret);
}
