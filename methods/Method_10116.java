/** 
 * Updates a user's online status and saves the login time and IP.
 * @param userId     the specified user id
 * @param ip         the specified IP, could be {@code null}
 * @param onlineFlag the specified online flag
 * @param force      the specified force flag to update
 */
@Transactional public void updateOnlineStatus(final String userId,final String ip,final boolean onlineFlag,final boolean force){
  try {
    final JSONObject user=userRepository.get(userId);
    if (null == user) {
      return;
    }
    final long updatedAt=user.optLong(UserExt.USER_UPDATE_TIME);
    final long now=System.currentTimeMillis();
    if (now - updatedAt < 1000 * 60 && !force) {
      return;
    }
    if (StringUtils.isNotBlank(ip)) {
      final JSONObject address=Geos.getAddress(ip);
      if (null != address) {
        final String country=address.optString(Common.COUNTRY);
        final String province=address.optString(Common.PROVINCE);
        final String city=address.optString(Common.CITY);
        user.put(UserExt.USER_COUNTRY,country);
        user.put(UserExt.USER_PROVINCE,province);
        user.put(UserExt.USER_CITY,city);
      }
      user.put(UserExt.USER_LATEST_LOGIN_IP,ip);
    }
    user.put(UserExt.USER_ONLINE_FLAG,onlineFlag);
    user.put(UserExt.USER_LATEST_LOGIN_TIME,now);
    user.put(UserExt.USER_UPDATE_TIME,now);
    userRepository.update(userId,user,UserExt.USER_COUNTRY,UserExt.USER_PROVINCE,UserExt.USER_CITY,UserExt.USER_LATEST_LOGIN_IP,UserExt.USER_ONLINE_FLAG,UserExt.USER_LATEST_LOGIN_TIME,UserExt.USER_UPDATE_TIME);
  }
 catch (  final RepositoryException e) {
    LOGGER.log(Level.ERROR,"Updates user online status failed [id=" + userId + ", ip=" + ip + ", flag=" + onlineFlag + "]",e);
  }
}
