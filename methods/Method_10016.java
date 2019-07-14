/** 
 * Gets most tag navigation domains.
 * @param fetchSize the specified fetch size
 * @return domains, returns an empty list if not found
 */
public List<JSONObject> getMostTagNaviDomains(final int fetchSize){
  final Query query=new Query().setFilter(new PropertyFilter(Domain.DOMAIN_NAV,FilterOperator.EQUAL,Domain.DOMAIN_NAV_C_ENABLED)).addSort(Domain.DOMAIN_SORT,SortDirection.ASCENDING).addSort(Domain.DOMAIN_TAG_COUNT,SortDirection.DESCENDING).addSort(Keys.OBJECT_ID,SortDirection.DESCENDING).setPage(1,fetchSize).setPageCount(1);
  try {
    final List<JSONObject> ret=CollectionUtils.jsonArrayToList(domainRepository.get(query).optJSONArray(Keys.RESULTS));
    for (    final JSONObject domain : ret) {
      final List<JSONObject> tags=getTags(domain.optString(Keys.OBJECT_ID));
      domain.put(Domain.DOMAIN_T_TAGS,(Object)tags);
    }
    return ret;
  }
 catch (  final Exception e) {
    LOGGER.log(Level.ERROR,"Gets most tag navigation domains failed",e);
    return Collections.emptyList();
  }
}
