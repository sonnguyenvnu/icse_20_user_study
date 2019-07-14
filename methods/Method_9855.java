/** 
 * Shows checkin ranking list.
 * @param context the specified context
 */
@RequestProcessing(value="/top/checkin",method=HttpMethod.GET) @Before({StopwatchStartAdvice.class,AnonymousViewCheck.class}) @After({PermissionGrant.class,StopwatchEndAdvice.class}) public void showCheckin(final RequestContext context){
  final AbstractFreeMarkerRenderer renderer=new SkinRenderer(context,"top/checkin.ftl");
  final Map<String,Object> dataModel=renderer.getDataModel();
  final List<JSONObject> users=activityQueryService.getTopCheckinUsers(Symphonys.TOP_CNT);
  dataModel.put(Common.TOP_CHECKIN_USERS,users);
  dataModelService.fillHeaderAndFooter(context,dataModel);
  dataModelService.fillRandomArticles(dataModel);
  dataModelService.fillSideHotArticles(dataModel);
  dataModelService.fillSideTags(dataModel);
  dataModelService.fillLatestCmts(dataModel);
}
