/** 
 * ?????????
 * @param pmsOperator
 * @return
 * @throws PermissionException
 * @throws Exception
 */
private String buildOperatorPermissionMenu(PmsOperator pmsOperator) throws PermissionException {
  String roleIds=pmsOperatorRoleService.getRoleIdsByOperatorId(pmsOperator.getId());
  if (StringUtils.isBlank(roleIds)) {
    LOG.error("==>??[" + pmsOperator.getLoginName() + "]???????????");
    throw new RuntimeException("?????????????");
  }
  return this.buildPermissionTree(roleIds);
}
