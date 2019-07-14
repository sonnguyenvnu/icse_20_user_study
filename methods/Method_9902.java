/** 
 * Gets user-tag relations by the specified tag id.
 * @param tagId          the specified tag id
 * @param currentPageNum the specified current page number, MUST greater then {@code 0}
 * @param pageSize       the specified page size(count of a page contains objects), MUST greater then {@code 0}
 * @return for example      <pre>{ "pagination": { "paginationPageCount": 88250 }, "rslts": [{ "oId": "", "tag_oId": "", "user_oId": userId, "type": "" // "creator"/"article"/"comment", a tag 'creator' is also an 'article' quoter }, ....] } </pre>
 * @throws RepositoryException repository exception
 */
public JSONObject getByTagId(final String tagId,final int currentPageNum,final int pageSize) throws RepositoryException {
  final Query query=new Query().setFilter(new PropertyFilter(Tag.TAG + "_" + Keys.OBJECT_ID,FilterOperator.EQUAL,tagId)).setPage(currentPageNum,pageSize).setPageCount(1);
  return get(query);
}
