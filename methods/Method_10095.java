/** 
 * Gets roles by the specified request json object.
 * @param currentPage the specified current page number
 * @param pageSize    the specified page size
 * @param windowSize  the specified window size
 * @return for example, <pre>{ "pagination": { "paginationPageCount": 100, "paginationPageNums": [1, 2, 3, 4, 5] }, "roles": [{ "oId": "", "roleName": "", "roleDescription": "", "roleUserCount": int, "permissions": [ { "oId": "adUpdateADSide", "permissionCategory": int }, .... ] }, ....] } </pre>
 * @see Pagination
 */
public JSONObject getRoles(final int currentPage,final int pageSize,final int windowSize){
  final JSONObject ret=new JSONObject();
  final Query query=new Query().setPage(currentPage,pageSize).addSort(Keys.OBJECT_ID,SortDirection.DESCENDING);
  JSONObject result;
  try {
    result=roleRepository.get(query);
  }
 catch (  final RepositoryException e) {
    LOGGER.log(Level.ERROR,"Gets roles failed",e);
    return null;
  }
  final int pageCount=result.optJSONObject(Pagination.PAGINATION).optInt(Pagination.PAGINATION_PAGE_COUNT);
  final JSONObject pagination=new JSONObject();
  ret.put(Pagination.PAGINATION,pagination);
  final List<Integer> pageNums=Paginator.paginate(currentPage,pageSize,pageCount,windowSize);
  pagination.put(Pagination.PAGINATION_PAGE_COUNT,pageCount);
  pagination.put(Pagination.PAGINATION_PAGE_NUMS,pageNums);
  final JSONArray data=result.optJSONArray(Keys.RESULTS);
  final List<JSONObject> roles=CollectionUtils.jsonArrayToList(data);
  try {
    for (    final JSONObject role : roles) {
      final List<JSONObject> permissions=new ArrayList<>();
      role.put(Permission.PERMISSIONS,(Object)permissions);
      final String roleId=role.optString(Keys.OBJECT_ID);
      final List<JSONObject> rolePermissions=rolePermissionRepository.getByRoleId(roleId);
      for (      final JSONObject rolePermission : rolePermissions) {
        final String permissionId=rolePermission.optString(Permission.PERMISSION_ID);
        final JSONObject permission=permissionRepository.get(permissionId);
        permissions.add(permission);
      }
      final Query userCountQuery=new Query().setFilter(new PropertyFilter(User.USER_ROLE,FilterOperator.EQUAL,roleId));
      final int count=(int)userRepository.count(userCountQuery);
      role.put(Role.ROLE_T_USER_COUNT,count);
      if (Strings.isNumeric(roleId)) {
        continue;
      }
      String roleName=role.optString(Role.ROLE_NAME);
      try {
        roleName=langPropsService.get(roleId + "NameLabel");
      }
 catch (      final Exception e) {
      }
      String roleDesc=role.optString(Role.ROLE_DESCRIPTION);
      try {
        roleDesc=langPropsService.get(roleId + "DescLabel");
      }
 catch (      final Exception e) {
      }
      role.put(Role.ROLE_NAME,roleName);
      role.put(Role.ROLE_DESCRIPTION,roleDesc);
    }
  }
 catch (  final RepositoryException e) {
    LOGGER.log(Level.ERROR,"Gets role permissions failed",e);
    return null;
  }
  Collections.sort(roles,(o1,o2) -> ((List)o2.opt(Permission.PERMISSIONS)).size() - ((List)o1.opt(Permission.PERMISSIONS)).size());
  ret.put(Role.ROLES,(Object)roles);
  return ret;
}
