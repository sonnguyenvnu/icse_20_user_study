/** 
 * Shows link ranking list.
 * @param context the specified context
 */
@RequestProcessing(value="/top/link",method=HttpMethod.GET) @Before({StopwatchStartAdvice.class,AnonymousViewCheck.class}) @After({PermissionGrant.class,StopwatchEndAdvice.class}) public void showLink(final RequestContext context){
  final AbstractFreeMarkerRenderer renderer=new SkinRenderer(context,"top/link.ftl");
  final Map<String,Object> dataModel=renderer.getDataModel();
  final List<JSONObject> topLinks=linkQueryService.getTopLink(Symphonys.TOP_CNT);
  dataModel.put(Common.TOP_LINKS,topLinks);
  dataModelService.fillHeaderAndFooter(context,dataModel);
  dataModelService.fillRandomArticles(dataModel);
  dataModelService.fillSideHotArticles(dataModel);
  dataModelService.fillSideTags(dataModel);
  dataModelService.fillLatestCmts(dataModel);
}
