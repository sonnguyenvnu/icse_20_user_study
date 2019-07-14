/** 
 * Get following user breezemoons.
 * @param userId     the specified user id, may be {@code null}
 * @param page       the specified page number
 * @param pageSize   the specified page size
 * @param windowSize the specified window size
 * @return for example, <pre>{ "pagination": { "paginationPageCount": 100, "paginationPageNums": [1, 2, 3, 4, 5] }, "breezemoons": [{ "id": "", "breezemoonContent": "" }, ....] } </pre>
 */
public JSONObject getFollowingUserBreezemoons(final String userId,final int page,final int pageSize,final int windowSize){
  final JSONObject ret=new JSONObject();
  final List<JSONObject> users=(List<JSONObject>)followQueryService.getFollowingUsers(userId,1,Integer.MAX_VALUE).opt(Keys.RESULTS);
  if (users.isEmpty()) {
    return getBreezemoons(userId,"",page,pageSize,windowSize);
  }
  final Query query=new Query().addSort(Keys.OBJECT_ID,SortDirection.DESCENDING).setPage(page,pageSize);
  final List<String> followingUserIds=new ArrayList<>();
  for (  final JSONObject user : users) {
    followingUserIds.add(user.optString(Keys.OBJECT_ID));
  }
  query.setFilter(CompositeFilterOperator.and(new PropertyFilter(Breezemoon.BREEZEMOON_STATUS,FilterOperator.EQUAL,Breezemoon.BREEZEMOON_STATUS_C_VALID),new PropertyFilter(Breezemoon.BREEZEMOON_AUTHOR_ID,FilterOperator.IN,followingUserIds)));
  JSONObject result;
  try {
    Stopwatchs.start("Query following user breezemoons");
    result=breezemoonRepository.get(query);
    final JSONArray data=result.optJSONArray(Keys.RESULTS);
    final List<JSONObject> bms=CollectionUtils.jsonArrayToList(data);
    if (bms.isEmpty()) {
      return getBreezemoons(userId,"",page,pageSize,windowSize);
    }
    organizeBreezemoons(userId,bms);
    ret.put(Breezemoon.BREEZEMOONS,(Object)bms);
  }
 catch (  final Exception e) {
    LOGGER.log(Level.ERROR,"Gets following user breezemoons failed",e);
    return null;
  }
 finally {
    Stopwatchs.end();
  }
  final JSONObject pagination=result.optJSONObject(Pagination.PAGINATION);
  ret.put(Pagination.PAGINATION,pagination);
  final int pageCount=pagination.optInt(Pagination.PAGINATION_PAGE_COUNT);
  final List<Integer> pageNums=Paginator.paginate(page,pageSize,pageCount,windowSize);
  pagination.put(Pagination.PAGINATION_PAGE_NUMS,pageNums);
  return ret;
}
