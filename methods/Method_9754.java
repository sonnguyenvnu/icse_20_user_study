/** 
 * Shows add article.
 * @param context the specified context
 */
@RequestProcessing(value="/admin/add-article",method=HttpMethod.GET) @Before({StopwatchStartAdvice.class,PermissionCheck.class}) @After({PermissionGrant.class,StopwatchEndAdvice.class}) public void showAddArticle(final RequestContext context){
  final AbstractFreeMarkerRenderer renderer=new SkinRenderer(context,"admin/add-article.ftl");
  final Map<String,Object> dataModel=renderer.getDataModel();
  dataModelService.fillHeaderAndFooter(context,dataModel);
}
