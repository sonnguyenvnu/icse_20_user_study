/** 
 * Gets domains by the specified request json object.
 * @param requestJSONObject the specified request json object, for example,{ "domainTitle": "", // optional "paginationCurrentPageNum": 1, "paginationPageSize": 20, "paginationWindowSize": 10 }, see  {@link Pagination} for more details
 * @param domainFields      the specified domain fields to return
 * @return for example,      <pre>{ "pagination": { "paginationPageCount": 100, "paginationPageNums": [1, 2, 3, 4, 5] }, "domains": [{ "oId": "", "domainTitle": "", "domainDescription": "", .... }, ....] } </pre>
 * @see Pagination
 */
public JSONObject getDomains(final JSONObject requestJSONObject,final List<String> domainFields){
  final JSONObject ret=new JSONObject();
  final int currentPageNum=requestJSONObject.optInt(Pagination.PAGINATION_CURRENT_PAGE_NUM);
  final int pageSize=requestJSONObject.optInt(Pagination.PAGINATION_PAGE_SIZE);
  final int windowSize=requestJSONObject.optInt(Pagination.PAGINATION_WINDOW_SIZE);
  final Query query=new Query().setPage(currentPageNum,pageSize).addSort(Domain.DOMAIN_SORT,SortDirection.ASCENDING).addSort(Domain.DOMAIN_TAG_COUNT,SortDirection.DESCENDING).addSort(Keys.OBJECT_ID,SortDirection.DESCENDING);
  for (  final String field : domainFields) {
    query.select(field);
  }
  if (requestJSONObject.has(Domain.DOMAIN_TITLE)) {
    query.setFilter(new PropertyFilter(Domain.DOMAIN_TITLE,FilterOperator.EQUAL,requestJSONObject.optString(Domain.DOMAIN_TITLE)));
  }
  JSONObject result;
  try {
    result=domainRepository.get(query);
  }
 catch (  final RepositoryException e) {
    LOGGER.log(Level.ERROR,"Gets domains failed",e);
    return null;
  }
  final int pageCount=result.optJSONObject(Pagination.PAGINATION).optInt(Pagination.PAGINATION_PAGE_COUNT);
  final JSONObject pagination=new JSONObject();
  ret.put(Pagination.PAGINATION,pagination);
  final List<Integer> pageNums=Paginator.paginate(currentPageNum,pageSize,pageCount,windowSize);
  pagination.put(Pagination.PAGINATION_PAGE_COUNT,pageCount);
  pagination.put(Pagination.PAGINATION_PAGE_NUMS,pageNums);
  final JSONArray data=result.optJSONArray(Keys.RESULTS);
  final List<JSONObject> domains=CollectionUtils.jsonArrayToList(data);
  ret.put(Domain.DOMAINS,domains);
  return ret;
}
