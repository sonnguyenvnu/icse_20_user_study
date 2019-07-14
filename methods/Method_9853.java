/** 
 * Shows balance ranking list.
 * @param context the specified context
 */
@RequestProcessing(value="/top/balance",method=HttpMethod.GET) @Before({StopwatchStartAdvice.class,AnonymousViewCheck.class}) @After({PermissionGrant.class,StopwatchEndAdvice.class}) public void showBalance(final RequestContext context){
  final AbstractFreeMarkerRenderer renderer=new SkinRenderer(context,"top/balance.ftl");
  final Map<String,Object> dataModel=renderer.getDataModel();
  final List<JSONObject> users=pointtransferQueryService.getTopBalanceUsers(Symphonys.TOP_CNT);
  dataModel.put(Common.TOP_BALANCE_USERS,users);
  dataModelService.fillHeaderAndFooter(context,dataModel);
  dataModelService.fillRandomArticles(dataModel);
  dataModelService.fillSideHotArticles(dataModel);
  dataModelService.fillSideTags(dataModel);
  dataModelService.fillLatestCmts(dataModel);
}
