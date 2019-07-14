/** 
 * Gets user-tag relations by the specified user id.
 * @param userId         the specified user id
 * @param currentPageNum the specified current page number, MUST greater then {@code 0}
 * @param pageSize       the specified page size(count of a page contains objects), MUST greater then {@code 0}
 * @return for example      <pre>{ "pagination": { "paginationPageCount": 88250 }, "rslts": [{ "oId": "", "tag_oId": "", "user_oId": userId, "type": "" // "creator"/"article"/"comment", a tag 'creator' is also an 'article' quoter }, ....] } </pre>
 * @throws RepositoryException repository exception
 */
public JSONObject getByUserId(final String userId,final int currentPageNum,final int pageSize) throws RepositoryException {
  final Query query=new Query().setFilter(new PropertyFilter(User.USER + "_" + Keys.OBJECT_ID,FilterOperator.EQUAL,userId)).setPage(currentPageNum,pageSize).setPageCount(1);
  return get(query);
}
