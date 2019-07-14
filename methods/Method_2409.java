/** 
 * ????id???????
 * @param upmsUserId
 * @return
 */
@Override @Cacheable(value="zheng-upms-rpc-service-ehcache",key="'selectUpmsRoleByUpmsUserId_' + #upmsUserId") public List<UpmsRole> selectUpmsRoleByUpmsUserIdByCache(Integer upmsUserId){
  return selectUpmsRoleByUpmsUserId(upmsUserId);
}
