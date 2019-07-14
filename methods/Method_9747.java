/** 
 * Shows role permissions.
 * @param context the specified context
 */
@RequestProcessing(value="/admin/role/{roleId}/permissions",method=HttpMethod.GET) @Before({StopwatchStartAdvice.class,PermissionCheck.class}) @After({PermissionGrant.class,StopwatchEndAdvice.class}) public void showRolePermissions(final RequestContext context){
  final String roleId=context.pathVar("roleId");
  final AbstractFreeMarkerRenderer renderer=new SkinRenderer(context,"admin/role-permissions.ftl");
  final Map<String,Object> dataModel=renderer.getDataModel();
  final JSONObject role=roleQueryService.getRole(roleId);
  dataModel.put(Role.ROLE,role);
  final Map<String,List<JSONObject>> categories=new TreeMap<>();
  final List<JSONObject> permissions=roleQueryService.getPermissionsGrant(roleId);
  for (  final JSONObject permission : permissions) {
    final String label=permission.optString(Keys.OBJECT_ID) + "PermissionLabel";
    permission.put(Permission.PERMISSION_T_LABEL,langPropsService.get(label));
    String category=permission.optString(Permission.PERMISSION_CATEGORY);
    category=langPropsService.get(category + "PermissionLabel");
    final List<JSONObject> categoryPermissions=categories.computeIfAbsent(category,k -> new ArrayList<>());
    categoryPermissions.add(permission);
  }
  dataModel.put(Permission.PERMISSION_T_CATEGORIES,categories);
  dataModelService.fillHeaderAndFooter(context,dataModel);
}
