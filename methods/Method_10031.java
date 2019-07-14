/** 
 * Gets follower users of the specified following user.
 * @param followingUserId the specified following user id
 * @param currentPageNum  the specified page number
 * @param pageSize        the specified page size
 * @return result json object, for example,      <pre>{ "paginationRecordCount": int, "rslts": java.util.List[{ User }, ....] } </pre>
 */
public JSONObject getFollowerUsers(final String followingUserId,final int currentPageNum,final int pageSize){
  final JSONObject ret=new JSONObject();
  final List<JSONObject> records=new ArrayList<>();
  ret.put(Keys.RESULTS,(Object)records);
  ret.put(Pagination.PAGINATION_RECORD_COUNT,0);
  try {
    final JSONObject result=getFollowers(followingUserId,Follow.FOLLOWING_TYPE_C_USER,currentPageNum,pageSize);
    final List<JSONObject> followers=(List<JSONObject>)result.opt(Keys.RESULTS);
    for (    final JSONObject follow : followers) {
      final String followerId=follow.optString(Follow.FOLLOWER_ID);
      final JSONObject user=userRepository.get(followerId);
      if (null == user) {
        LOGGER.log(Level.WARN,"Not found user [id=" + followerId + "]");
        continue;
      }
      avatarQueryService.fillUserAvatarURL(user);
      records.add(user);
    }
    ret.put(Pagination.PAGINATION_RECORD_COUNT,result.optInt(Pagination.PAGINATION_RECORD_COUNT));
  }
 catch (  final RepositoryException e) {
    LOGGER.log(Level.ERROR,"Gets follower users of following user [id=" + followingUserId + "] failed",e);
  }
  return ret;
}
