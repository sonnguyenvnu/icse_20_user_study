/** 
 * Shows roles.
 * @param context the specified context
 */
@RequestProcessing(value="/admin/roles",method=HttpMethod.GET) @Before({StopwatchStartAdvice.class,PermissionCheck.class}) @After({PermissionGrant.class,StopwatchEndAdvice.class}) public void showRoles(final RequestContext context){
  final AbstractFreeMarkerRenderer renderer=new SkinRenderer(context,"admin/roles.ftl");
  final Map<String,Object> dataModel=renderer.getDataModel();
  final JSONObject result=roleQueryService.getRoles(1,Integer.MAX_VALUE,10);
  final List<JSONObject> roles=(List<JSONObject>)result.opt(Role.ROLES);
  dataModel.put(Role.ROLES,roles);
  dataModelService.fillHeaderAndFooter(context,dataModel);
}
