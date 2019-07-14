/** 
 * Shows admin index.
 * @param context the specified context
 */
@RequestProcessing(value="/admin",method=HttpMethod.GET) @Before({StopwatchStartAdvice.class,PermissionCheck.class}) @After({PermissionGrant.class,StopwatchEndAdvice.class}) public void showAdminIndex(final RequestContext context){
  final AbstractFreeMarkerRenderer renderer=new SkinRenderer(context,"admin/index.ftl");
  final Map<String,Object> dataModel=renderer.getDataModel();
  dataModelService.fillHeaderAndFooter(context,dataModel);
  dataModel.put(Common.ONLINE_VISITOR_CNT,optionQueryService.getOnlineVisitorCount());
  dataModel.put(Common.ONLINE_MEMBER_CNT,optionQueryService.getOnlineMemberCount());
  final JSONObject statistic=optionQueryService.getStatistic();
  dataModel.put(Option.CATEGORY_C_STATISTIC,statistic);
}
