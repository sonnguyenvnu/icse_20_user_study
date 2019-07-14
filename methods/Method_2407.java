/** 
 * ????id????????
 * @param upmsUserId
 * @return
 */
@Override @Cacheable(value="zheng-upms-rpc-service-ehcache",key="'selectUpmsPermissionByUpmsUserId_' + #upmsUserId") public List<UpmsPermission> selectUpmsPermissionByUpmsUserIdByCache(Integer upmsUserId){
  return selectUpmsPermissionByUpmsUserId(upmsUserId);
}
