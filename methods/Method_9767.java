/** 
 * Shows add domain.
 * @param context the specified context
 */
@RequestProcessing(value="/admin/add-domain",method=HttpMethod.GET) @Before({StopwatchStartAdvice.class,PermissionCheck.class}) @After({PermissionGrant.class,StopwatchEndAdvice.class}) public void showAddDomain(final RequestContext context){
  final AbstractFreeMarkerRenderer renderer=new SkinRenderer(context,"admin/add-domain.ftl");
  final Map<String,Object> dataModel=renderer.getDataModel();
  dataModelService.fillHeaderAndFooter(context,dataModel);
}
