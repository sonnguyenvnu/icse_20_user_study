/** 
 * ????id???????
 * @param upmsUserId
 * @return
 */
@Override public List<UpmsRole> selectUpmsRoleByUpmsUserId(Integer upmsUserId){
  UpmsUser upmsUser=upmsUserMapper.selectByPrimaryKey(upmsUserId);
  if (null == upmsUser || 1 == upmsUser.getLocked()) {
    LOGGER.info("selectUpmsRoleByUpmsUserId : upmsUserId={}",upmsUserId);
    return null;
  }
  List<UpmsRole> upmsRoles=upmsApiMapper.selectUpmsRoleByUpmsUserId(upmsUserId);
  return upmsRoles;
}
