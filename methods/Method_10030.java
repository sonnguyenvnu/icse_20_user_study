/** 
 * Gets watcher users of the specified watching article.
 * @param avatarViewMode    the specified avatar view mode
 * @param watchingArticleId the specified watching article id
 * @param currentPageNum    the specified page number
 * @param pageSize          the specified page size
 * @return result json object, for example,      <pre>{ "paginationRecordCount": int, "rslts": java.util.List[{ User }, ....] } </pre>
 */
public JSONObject getArticleWatchers(final int avatarViewMode,final String watchingArticleId,final int currentPageNum,final int pageSize){
  final JSONObject ret=new JSONObject();
  final List<JSONObject> records=new ArrayList<>();
  ret.put(Keys.RESULTS,(Object)records);
  ret.put(Pagination.PAGINATION_RECORD_COUNT,0);
  try {
    final JSONObject result=getFollowers(watchingArticleId,Follow.FOLLOWING_TYPE_C_ARTICLE_WATCH,currentPageNum,pageSize);
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
    LOGGER.log(Level.ERROR,"Gets watcher users of watching article [id=" + watchingArticleId + "] failed",e);
  }
  return ret;
}
