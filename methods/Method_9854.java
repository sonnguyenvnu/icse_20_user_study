/** 
 * Shows consumption ranking list.
 * @param context the specified context
 */
@RequestProcessing(value="/top/consumption",method=HttpMethod.GET) @Before({StopwatchStartAdvice.class,AnonymousViewCheck.class}) @After({PermissionGrant.class,StopwatchEndAdvice.class}) public void showConsumption(final RequestContext context){
  final AbstractFreeMarkerRenderer renderer=new SkinRenderer(context,"top/consumption.ftl");
  final Map<String,Object> dataModel=renderer.getDataModel();
  final List<JSONObject> users=pointtransferQueryService.getTopConsumptionUsers(Symphonys.TOP_CNT);
  dataModel.put(Common.TOP_CONSUMPTION_USERS,users);
  dataModelService.fillHeaderAndFooter(context,dataModel);
  dataModelService.fillRandomArticles(dataModel);
  dataModelService.fillSideHotArticles(dataModel);
  dataModelService.fillSideTags(dataModel);
  dataModelService.fillLatestCmts(dataModel);
}
