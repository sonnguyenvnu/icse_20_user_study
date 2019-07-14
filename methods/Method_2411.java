/** 
 * ????id????????
 * @param upmsUserId
 * @return
 */
@Override public List<UpmsUserPermission> selectUpmsUserPermissionByUpmsUserId(Integer upmsUserId){
  UpmsUserPermissionExample upmsUserPermissionExample=new UpmsUserPermissionExample();
  upmsUserPermissionExample.createCriteria().andUserIdEqualTo(upmsUserId);
  List<UpmsUserPermission> upmsUserPermissions=upmsUserPermissionMapper.selectByExample(upmsUserPermissionExample);
  return upmsUserPermissions;
}
