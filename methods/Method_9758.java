/** 
 * Shows a user.
 * @param context the specified context
 */
@RequestProcessing(value="/admin/user/{userId}",method=HttpMethod.GET) @Before({StopwatchStartAdvice.class,PermissionCheck.class}) @After({PermissionGrant.class,StopwatchEndAdvice.class}) public void showUser(final RequestContext context){
  final String userId=context.pathVar("userId");
  final AbstractFreeMarkerRenderer renderer=new SkinRenderer(context,"admin/user.ftl");
  final Map<String,Object> dataModel=renderer.getDataModel();
  final JSONObject user=userQueryService.getUser(userId);
  Escapes.escapeHTML(user);
  dataModel.put(User.USER,user);
  final JSONObject result=roleQueryService.getRoles(1,Integer.MAX_VALUE,10);
  final List<JSONObject> roles=(List<JSONObject>)result.opt(Role.ROLES);
  dataModel.put(Role.ROLES,roles);
  dataModelService.fillHeaderAndFooter(context,dataModel);
}
