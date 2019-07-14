/** 
 * Get breezemoons by the specified request json object.
 * @param requestJSONObject the specified request json object, for example,"paginationCurrentPageNum": 1, "paginationPageSize": 20, "paginationWindowSize": 10, , see  {@link Pagination} for more details
 * @param fields            the specified fields to return
 * @return for example,      <pre>{ "pagination": { "paginationPageCount": 100, "paginationPageNums": [1, 2, 3, 4, 5] }, "breezemoons": [{ "oId": "", "breezemoonContent": "", "breezemoonCreateTime": "", .... }, ....] } </pre>
 * @see Pagination
 */
public JSONObject getBreezemoons(final JSONObject requestJSONObject,final List<String> fields){
  final JSONObject ret=new JSONObject();
  final int currentPageNum=requestJSONObject.optInt(Pagination.PAGINATION_CURRENT_PAGE_NUM);
  final int pageSize=requestJSONObject.optInt(Pagination.PAGINATION_PAGE_SIZE);
  final int windowSize=requestJSONObject.optInt(Pagination.PAGINATION_WINDOW_SIZE);
  final Query query=new Query().setPage(currentPageNum,pageSize).addSort(Keys.OBJECT_ID,SortDirection.DESCENDING);
  for (  final String field : fields) {
    query.select(field);
  }
  JSONObject result;
  try {
    result=breezemoonRepository.get(query);
  }
 catch (  final RepositoryException e) {
    LOGGER.log(Level.ERROR,"Get breezemoons failed",e);
    return null;
  }
  final int pageCount=result.optJSONObject(Pagination.PAGINATION).optInt(Pagination.PAGINATION_PAGE_COUNT);
  final JSONObject pagination=new JSONObject();
  ret.put(Pagination.PAGINATION,pagination);
  final List<Integer> pageNums=Paginator.paginate(currentPageNum,pageSize,pageCount,windowSize);
  pagination.put(Pagination.PAGINATION_PAGE_COUNT,pageCount);
  pagination.put(Pagination.PAGINATION_PAGE_NUMS,pageNums);
  final JSONArray data=result.optJSONArray(Keys.RESULTS);
  final List<JSONObject> breezemoons=CollectionUtils.jsonArrayToList(data);
  try {
    organizeBreezemoons("admin",breezemoons);
  }
 catch (  final Exception e) {
    LOGGER.log(Level.ERROR,"Organize breezemoons failed",e);
    return null;
  }
  ret.put(Breezemoon.BREEZEMOONS,breezemoons);
  return ret;
}
