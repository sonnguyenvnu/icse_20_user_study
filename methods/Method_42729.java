/** 
 * ??????????ID,???????????????
 * @param roleIds
 * @return
 * @throws PermissionException
 */
@SuppressWarnings("rawtypes") public String buildPermissionTree(String roleIds) throws PermissionException {
  List treeData=null;
  try {
    treeData=pmsMenuService.listByRoleIds(roleIds);
    if (StringUtil.isEmpty(treeData)) {
      LOG.error("??????????");
      throw new PermissionException(PermissionException.PERMISSION_USER_NOT_MENU,"???????????");
    }
  }
 catch (  Exception e) {
    LOG.error("????????????",e);
    throw new PermissionException(PermissionException.PERMISSION_QUERY_MENU_BY_ROLE_ERROR,"????????????");
  }
  StringBuffer strJson=new StringBuffer();
  buildAdminPermissionTree("0",strJson,treeData);
  return strJson.toString();
}
