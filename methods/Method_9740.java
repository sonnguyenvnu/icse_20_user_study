/** 
 * Starts eating snake.
 * @param context the specified context
 */
@RequestProcessing(value="/activity/eating-snake/start",method=HttpMethod.POST) @Before({StopwatchStartAdvice.class,LoginCheck.class,CSRFCheck.class}) @After(StopwatchEndAdvice.class) public void startEatingSnake(final RequestContext context){
  final HttpServletRequest request=context.getRequest();
  final JSONObject currentUser=Sessions.getUser();
  final String fromId=currentUser.optString(Keys.OBJECT_ID);
  final JSONObject ret=activityMgmtService.startEatingSnake(fromId);
  context.renderJSON(ret);
}
