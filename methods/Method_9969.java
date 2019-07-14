/** 
 * Get breezemoons with the specified user id, current page number.
 * @param currentUserId the specified current user id, may be {@code null}
 * @param authorId      the specified user id, empty "" for all users
 * @param page          the specified current page number
 * @param pageSize      the specified page size
 * @param windowSize    the specified window size
 * @return for example, <pre>{ "pagination": { "paginationPageCount": 100, "paginationPageNums": [1, 2, 3, 4, 5] }, "breezemoons": [{ "id": "", "breezemoonContent": "" }, ....] } </pre>
 * @see Pagination
 */
public JSONObject getBreezemoons(final String currentUserId,final String authorId,final int page,final int pageSize,final int windowSize){
  final JSONObject ret=new JSONObject();
  CompositeFilter filter;
  final Filter statusFilter=new PropertyFilter(Breezemoon.BREEZEMOON_STATUS,FilterOperator.EQUAL,Breezemoon.BREEZEMOON_STATUS_C_VALID);
  if (StringUtils.isNotBlank(authorId)) {
    filter=CompositeFilterOperator.and(new PropertyFilter(Breezemoon.BREEZEMOON_AUTHOR_ID,FilterOperator.EQUAL,authorId),statusFilter);
  }
 else {
    filter=CompositeFilterOperator.and(new PropertyFilter(Breezemoon.BREEZEMOON_AUTHOR_ID,FilterOperator.NOT_EQUAL,authorId),statusFilter);
  }
  final Query query=new Query().setFilter(filter).addSort(Keys.OBJECT_ID,SortDirection.DESCENDING).setPage(page,20);
  JSONObject result;
  try {
    result=breezemoonRepository.get(query);
  }
 catch (  final Exception e) {
    LOGGER.log(Level.ERROR,"Get breezemoons failed",e);
    return null;
  }
  final JSONObject pagination=result.optJSONObject(Pagination.PAGINATION);
  ret.put(Pagination.PAGINATION,pagination);
  final int pageCount=pagination.optInt(Pagination.PAGINATION_PAGE_COUNT);
  final List<Integer> pageNums=Paginator.paginate(page,pageSize,pageCount,windowSize);
  pagination.put(Pagination.PAGINATION_PAGE_NUMS,pageNums);
  final JSONArray data=result.optJSONArray(Keys.RESULTS);
  final List<JSONObject> bms=CollectionUtils.jsonArrayToList(data);
  try {
    organizeBreezemoons(currentUserId,bms);
  }
 catch (  final Exception e) {
    LOGGER.log(Level.ERROR,"Get breezemoons failed",e);
    return null;
  }
  ret.put(Breezemoon.BREEZEMOONS,(Object)bms);
  return ret;
}
