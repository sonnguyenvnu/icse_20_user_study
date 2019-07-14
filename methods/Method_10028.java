/** 
 * Gets following articles of the specified follower.
 * @param followerId     the specified follower id
 * @param currentPageNum the specified page number
 * @param pageSize       the specified page size
 * @return result json object, for example,      <pre>{ "paginationRecordCount": int, "rslts": java.util.List[{ Article }, ....] } </pre>
 */
public JSONObject getFollowingArticles(final String followerId,final int currentPageNum,final int pageSize){
  final JSONObject ret=new JSONObject();
  final List<JSONObject> records=new ArrayList<>();
  ret.put(Keys.RESULTS,(Object)records);
  ret.put(Pagination.PAGINATION_RECORD_COUNT,0);
  try {
    final JSONObject result=getFollowings(followerId,Follow.FOLLOWING_TYPE_C_ARTICLE,currentPageNum,pageSize);
    final List<JSONObject> followings=(List<JSONObject>)result.opt(Keys.RESULTS);
    final ArticleQueryService articleQueryService=BeanManager.getInstance().getReference(ArticleQueryService.class);
    for (    final JSONObject follow : followings) {
      final String followingId=follow.optString(Follow.FOLLOWING_ID);
      final JSONObject article=articleRepository.get(followingId);
      if (null == article) {
        LOGGER.log(Level.WARN,"Not found article [id=" + followingId + "]");
        continue;
      }
      articleQueryService.organizeArticle(article);
      records.add(article);
    }
    ret.put(Pagination.PAGINATION_RECORD_COUNT,result.optInt(Pagination.PAGINATION_RECORD_COUNT));
  }
 catch (  final RepositoryException e) {
    LOGGER.log(Level.ERROR,"Get following articles of follower [id=" + followerId + "] failed",e);
  }
  return ret;
}
