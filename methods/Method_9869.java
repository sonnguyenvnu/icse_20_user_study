/** 
 * Fills home user.
 * @param dataModel the specified data model
 * @param user      the specified user
 */
static void fillHomeUser(final Map<String,Object> dataModel,final JSONObject user,final RoleQueryService roleQueryService){
  Escapes.escapeHTML(user);
  dataModel.put(User.USER,user);
  final String roleId=user.optString(User.USER_ROLE);
  final JSONObject role=roleQueryService.getRole(roleId);
  user.put(Role.ROLE_NAME,role.optString(Role.ROLE_NAME));
  user.put(UserExt.USER_T_CREATE_TIME,new Date(user.optLong(Keys.OBJECT_ID)));
}
