/** 
 * Gets domain-tag relations by the specified domain id.
 * @param domainId       the specified domain id
 * @param currentPageNum the specified current page number, MUST greater then {@code 0}
 * @param pageSize       the specified page size(count of a page contains objects), MUST greater then {@code 0}
 * @return for example      <pre>{ "pagination": { "paginationPageCount": 88250 }, "rslts": [{ "oId": "", "domain_oId": domainId, "tag_oId": "" }, ....] } </pre>
 * @throws RepositoryException repository exception
 */
public JSONObject getByDomainId(final String domainId,final int currentPageNum,final int pageSize) throws RepositoryException {
  final Query query=new Query().setFilter(new PropertyFilter(Domain.DOMAIN + "_" + Keys.OBJECT_ID,FilterOperator.EQUAL,domainId)).setPage(currentPageNum,pageSize).setPageCount(1);
  return get(query);
}
