/** 
 * Collects 1A0001.
 * @param context the specified context
 */
@RequestProcessing(value="/activity/1A0001/collect",method=HttpMethod.POST) @Before({StopwatchStartAdvice.class,LoginCheck.class,Activity1A0001CollectValidation.class}) @After(StopwatchEndAdvice.class) public void collect1A0001(final RequestContext context){
  final JSONObject currentUser=Sessions.getUser();
  final String userId=currentUser.optString(Keys.OBJECT_ID);
  final JSONObject ret=activityMgmtService.collect1A0001(userId);
  context.renderJSON(ret);
}
