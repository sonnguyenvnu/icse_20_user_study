/** 
 * ????id????????
 * @param upmsUserId
 * @return
 */
@Override public List<UpmsPermission> selectUpmsPermissionByUpmsUserId(Integer upmsUserId){
  UpmsUser upmsUser=upmsUserMapper.selectByPrimaryKey(upmsUserId);
  if (null == upmsUser || 1 == upmsUser.getLocked()) {
    LOGGER.info("selectUpmsPermissionByUpmsUserId : upmsUserId={}",upmsUserId);
    return null;
  }
  List<UpmsPermission> upmsPermissions=upmsApiMapper.selectUpmsPermissionByUpmsUserId(upmsUserId);
  return upmsPermissions;
}
