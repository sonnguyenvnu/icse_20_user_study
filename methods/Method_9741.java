/** 
 * Collects eating snake.
 * @param context the specified context
 */
@RequestProcessing(value="/activity/eating-snake/collect",method=HttpMethod.POST) @Before({StopwatchStartAdvice.class,LoginCheck.class}) @After({CSRFToken.class,StopwatchEndAdvice.class}) public void collectEatingSnake(final RequestContext context){
  final AbstractFreeMarkerRenderer renderer=new SkinRenderer(context,"activity/eating-snake.ftl");
  JSONObject requestJSONObject;
  try {
    requestJSONObject=context.requestJSON();
    final int score=requestJSONObject.optInt("score");
    final JSONObject user=Sessions.getUser();
    final JSONObject ret=activityMgmtService.collectEatingSnake(user.optString(Keys.OBJECT_ID),score);
    context.renderJSON(ret);
  }
 catch (  final Exception e) {
    LOGGER.log(Level.ERROR,"Collects eating snake game failed",e);
    context.renderJSON(false).renderMsg("err....");
  }
}
