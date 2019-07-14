/** 
 * Shows add reserved word.
 * @param context the specified context
 */
@RequestProcessing(value="/admin/add-reserved-word",method=HttpMethod.GET) @Before({StopwatchStartAdvice.class,PermissionCheck.class}) @After({PermissionGrant.class,StopwatchEndAdvice.class}) public void showAddReservedWord(final RequestContext context){
  final AbstractFreeMarkerRenderer renderer=new SkinRenderer(context,"admin/add-reserved-word.ftl");
  final Map<String,Object> dataModel=renderer.getDataModel();
  dataModelService.fillHeaderAndFooter(context,dataModel);
}
