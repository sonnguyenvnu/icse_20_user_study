/** 
 * Gets the top consumption users with the specified fetch size.
 * @param fetchSize the specified fetch size
 * @return users, returns an empty list if not found
 */
public List<JSONObject> getTopConsumptionUsers(final int fetchSize){
  final List<JSONObject> ret=new ArrayList<>();
  final Query query=new Query().addSort(UserExt.USER_USED_POINT,SortDirection.DESCENDING).setPage(1,fetchSize).setFilter(new PropertyFilter(UserExt.USER_JOIN_USED_POINT_RANK,FilterOperator.EQUAL,UserExt.USER_JOIN_XXX_C_JOIN));
  final int moneyUnit=Symphonys.POINT_EXCHANGE_UNIT;
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
      user.put(Common.MONEY,(int)Math.floor(user.optInt(UserExt.USER_USED_POINT) / moneyUnit));
      avatarQueryService.fillUserAvatarURL(user);
      ret.add(user);
    }
  }
 catch (  final RepositoryException e) {
    LOGGER.log(Level.ERROR,"Gets top consumption users error",e);
  }
  return ret;
}
