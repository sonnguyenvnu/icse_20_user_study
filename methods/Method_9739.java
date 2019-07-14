/** 
 * Shows eating snake.
 * @param context the specified context
 */
@RequestProcessing(value="/activity/eating-snake",method=HttpMethod.GET) @Before({StopwatchStartAdvice.class,LoginCheck.class}) @After({CSRFToken.class,PermissionGrant.class,StopwatchEndAdvice.class}) public void showEatingSnake(final RequestContext context){
  final AbstractFreeMarkerRenderer renderer=new SkinRenderer(context,"activity/eating-snake.ftl");
  final Map<String,Object> dataModel=renderer.getDataModel();
  dataModelService.fillHeaderAndFooter(context,dataModel);
  dataModelService.fillRandomArticles(dataModel);
  dataModelService.fillSideHotArticles(dataModel);
  dataModelService.fillSideTags(dataModel);
  dataModelService.fillLatestCmts(dataModel);
  final List<JSONObject> maxUsers=activityQueryService.getTopEatingSnakeUsersMax(10);
  dataModel.put("maxUsers",maxUsers);
  final List<JSONObject> sumUsers=activityQueryService.getTopEatingSnakeUsersSum(10);
  dataModel.put("sumUsers",sumUsers);
  final JSONObject user=Sessions.getUser();
  final String userId=user.optString(Keys.OBJECT_ID);
  final int startPoint=activityQueryService.getEatingSnakeAvgPoint(userId);
  String pointActivityEatingSnake=langPropsService.get("activityStartEatingSnakeTipLabel");
  pointActivityEatingSnake=pointActivityEatingSnake.replace("{point}",String.valueOf(startPoint));
  dataModel.put("activityStartEatingSnakeTipLabel",pointActivityEatingSnake);
}
