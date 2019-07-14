/** 
 * Gets tag-article relations by the specified tag id.
 * @param tagId          the specified tag id
 * @param currentPageNum the specified current page number, MUST greater then {@code 0}
 * @param pageSize       the specified page size(count of a page contains objects), MUST greater then {@code 0}
 * @return for example      <pre>{ "pagination": { "paginationPageCount": 88250 }, "rslts": [{ "oId": "", "tag_oId": tagId, "article_oId": "" }, ....] } </pre>
 * @throws RepositoryException repository exception
 */
public JSONObject getByTagId(final String tagId,final int currentPageNum,final int pageSize) throws RepositoryException {
  final Query query=new Query().setFilter(new PropertyFilter(Tag.TAG + "_" + Keys.OBJECT_ID,FilterOperator.EQUAL,tagId)).addSort(Article.ARTICLE + "_" + Keys.OBJECT_ID,SortDirection.DESCENDING).setPage(currentPageNum,pageSize).setPageCount(1);
  return get(query);
}
