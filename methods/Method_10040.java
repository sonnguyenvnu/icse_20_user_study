/** 
 * Gets invitecodes by the specified request json object.
 * @param requestJSONObject the specified request json object, for example,{ "paginationCurrentPageNum": 1, "paginationPageSize": 20, "paginationWindowSize": 10 }
 * @return for example,      <pre>{ "pagination": { "paginationPageCount": 100, "paginationPageNums": [1, 2, 3, 4, 5] }, "invitecodes": [{ "oId": "", "code": "", "memo": "", .... }, ....] } </pre>
 * @see Pagination
 */
public JSONObject getInvitecodes(final JSONObject requestJSONObject){
  final JSONObject ret=new JSONObject();
  final int currentPageNum=requestJSONObject.optInt(Pagination.PAGINATION_CURRENT_PAGE_NUM);
  final int pageSize=requestJSONObject.optInt(Pagination.PAGINATION_PAGE_SIZE);
  final int windowSize=requestJSONObject.optInt(Pagination.PAGINATION_WINDOW_SIZE);
  final Query query=new Query().setPage(currentPageNum,pageSize).addSort(Invitecode.STATUS,SortDirection.DESCENDING).addSort(Keys.OBJECT_ID,SortDirection.DESCENDING);
  JSONObject result;
  try {
    result=invitecodeRepository.get(query);
  }
 catch (  final RepositoryException e) {
    LOGGER.log(Level.ERROR,"Gets invitecodes failed",e);
    return null;
  }
  final int pageCount=result.optJSONObject(Pagination.PAGINATION).optInt(Pagination.PAGINATION_PAGE_COUNT);
  final JSONObject pagination=new JSONObject();
  ret.put(Pagination.PAGINATION,pagination);
  final List<Integer> pageNums=Paginator.paginate(currentPageNum,pageSize,pageCount,windowSize);
  pagination.put(Pagination.PAGINATION_PAGE_COUNT,pageCount);
  pagination.put(Pagination.PAGINATION_PAGE_NUMS,pageNums);
  final JSONArray data=result.optJSONArray(Keys.RESULTS);
  final List<JSONObject> invitecodes=CollectionUtils.jsonArrayToList(data);
  ret.put(Invitecode.INVITECODES,invitecodes);
  return ret;
}
