/** 
 * Shows add tag.
 * @param context the specified context
 */
@RequestProcessing(value="/admin/add-tag",method=HttpMethod.GET) @Before({StopwatchStartAdvice.class,PermissionCheck.class}) @After({PermissionGrant.class,StopwatchEndAdvice.class}) public void showAddTag(final RequestContext context){
  final AbstractFreeMarkerRenderer renderer=new SkinRenderer(context,"admin/add-tag.ftl");
  final Map<String,Object> dataModel=renderer.getDataModel();
  dataModelService.fillHeaderAndFooter(context,dataModel);
}
