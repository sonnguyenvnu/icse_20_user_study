/** 
 * Gets tag-tag relations by the specified tag2 id.
 * @param tag2Id         the specified tag2 id
 * @param currentPageNum the specified current page number, MUST greater then {@code 0}
 * @param pageSize       the specified page size(count of a page contains objects), MUST greater then {@code 0}
 * @return for example      <pre>{ "pagination": { "paginationPageCount": 88250 }, "rslts": [{ "oId": "", "tag1_oId": "", "tag2_oId": tag2Id, "weight": int }, ....] } </pre>
 * @throws RepositoryException repository exception
 */
public JSONObject getByTag2Id(final String tag2Id,final int currentPageNum,final int pageSize) throws RepositoryException {
  final List<Filter> filters=new ArrayList<>();
  filters.add(new PropertyFilter(Tag.TAG + "2_" + Keys.OBJECT_ID,FilterOperator.EQUAL,tag2Id));
  filters.add(new PropertyFilter(Common.WEIGHT,FilterOperator.GREATER_THAN_OR_EQUAL,Symphonys.TAG_RELATED_WEIGHT));
  final Query query=new Query().setFilter(new CompositeFilter(CompositeFilterOperator.AND,filters)).setPage(currentPageNum,pageSize).setPageCount(1).addSort(Common.WEIGHT,SortDirection.DESCENDING);
  return get(query);
}
