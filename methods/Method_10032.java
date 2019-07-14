/** 
 * Gets the followings of a follower specified by the given follower id and following type.
 * @param followerId     the given follower id
 * @param followingType  the specified following type
 * @param currentPageNum the specified current page number
 * @param pageSize       the specified page size
 * @return result json object, for example,      <pre>{ "paginationRecordCount": int, "rslts": java.util.List[{ "oId": "", "followerId": "", "followingId": "", "followingType": int }, ....] } </pre>
 * @throws RepositoryException repository exception
 */
private JSONObject getFollowings(final String followerId,final int followingType,final int currentPageNum,final int pageSize) throws RepositoryException {
  final List<Filter> filters=new ArrayList<>();
  filters.add(new PropertyFilter(Follow.FOLLOWER_ID,FilterOperator.EQUAL,followerId));
  filters.add(new PropertyFilter(Follow.FOLLOWING_TYPE,FilterOperator.EQUAL,followingType));
  final Query query=new Query().addSort(Keys.OBJECT_ID,SortDirection.DESCENDING).setFilter(new CompositeFilter(CompositeFilterOperator.AND,filters)).setPage(currentPageNum,pageSize);
  final JSONObject result=followRepository.get(query);
  final List<JSONObject> records=CollectionUtils.jsonArrayToList(result.optJSONArray(Keys.RESULTS));
  final int recordCnt=result.optJSONObject(Pagination.PAGINATION).optInt(Pagination.PAGINATION_RECORD_COUNT);
  final JSONObject ret=new JSONObject();
  ret.put(Keys.RESULTS,(Object)records);
  ret.put(Pagination.PAGINATION_RECORD_COUNT,recordCnt);
  return ret;
}
