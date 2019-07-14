/** 
 * ????id????????
 * @param upmsRoleId
 * @return
 */
@Override public List<UpmsRolePermission> selectUpmsRolePermisstionByUpmsRoleId(Integer upmsRoleId){
  UpmsRolePermissionExample upmsRolePermissionExample=new UpmsRolePermissionExample();
  upmsRolePermissionExample.createCriteria().andRoleIdEqualTo(upmsRoleId);
  List<UpmsRolePermission> upmsRolePermissions=upmsRolePermissionMapper.selectByExample(upmsRolePermissionExample);
  return upmsRolePermissions;
}
