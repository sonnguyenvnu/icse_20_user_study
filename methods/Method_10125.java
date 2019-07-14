/** 
 * Get nice users with the specified fetch size.
 * @param fetchSize the specified fetch size
 * @return a list of users
 */
public List<JSONObject> getNiceUsers(int fetchSize){
  final List<JSONObject> ret=new ArrayList<>();
  final int RANGE_SIZE=64;
  try {
    final Query userQuery=new Query().setPage(1,RANGE_SIZE).setPageCount(1).setFilter(new PropertyFilter(UserExt.USER_STATUS,FilterOperator.EQUAL,UserExt.USER_STATUS_C_VALID)).addSort(UserExt.USER_ARTICLE_COUNT,SortDirection.DESCENDING).addSort(UserExt.USER_COMMENT_COUNT,SortDirection.DESCENDING);
    final JSONArray rangeUsers=userRepository.get(userQuery).optJSONArray(Keys.RESULTS);
    final int realLen=rangeUsers.length();
    if (realLen < fetchSize) {
      fetchSize=realLen;
    }
    final List<Integer> indices=CollectionUtils.getRandomIntegers(0,realLen,fetchSize);
    for (    final Integer index : indices) {
      ret.add(rangeUsers.getJSONObject(index));
    }
    for (    final JSONObject selectedUser : ret) {
      avatarQueryService.fillUserAvatarURL(selectedUser);
    }
  }
 catch (  final Exception e) {
    LOGGER.log(Level.ERROR,"Get nice users failed",e);
  }
  return ret;
}
