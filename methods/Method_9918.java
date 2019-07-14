/** 
 * Gets the top checkin users with the specified fetch size.
 * @param fetchSize the specified fetch size
 * @return users, returns an empty list if not found
 */
public List<JSONObject> getTopCheckinUsers(final int fetchSize){
  final List<JSONObject> ret=new ArrayList<>();
  final Query query=new Query().addSort(UserExt.USER_LONGEST_CHECKIN_STREAK,SortDirection.DESCENDING).addSort(UserExt.USER_CURRENT_CHECKIN_STREAK,SortDirection.DESCENDING).setPage(1,fetchSize);
  try {
    final JSONObject result=userRepository.get(query);
    final List<JSONObject> users=CollectionUtils.jsonArrayToList(result.optJSONArray(Keys.RESULTS));
    for (    final JSONObject user : users) {
      if (UserExt.USER_APP_ROLE_C_HACKER == user.optInt(UserExt.USER_APP_ROLE)) {
        user.put(UserExt.USER_T_POINT_HEX,Integer.toHexString(user.optInt(UserExt.USER_POINT)));
      }
 else {
        user.put(UserExt.USER_T_POINT_CC,UserExt.toCCString(user.optInt(UserExt.USER_POINT)));
      }
      avatarQueryService.fillUserAvatarURL(user);
      ret.add(user);
    }
  }
 catch (  final RepositoryException e) {
    LOGGER.log(Level.ERROR,"Gets top checkin users error",e);
  }
  return ret;
}
