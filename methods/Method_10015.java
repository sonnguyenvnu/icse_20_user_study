/** 
 * Gets all domains.
 * @return domains, returns an empty list if not found
 */
public List<JSONObject> getAllDomains(){
  final Query query=new Query().addSort(Domain.DOMAIN_SORT,SortDirection.ASCENDING).addSort(Domain.DOMAIN_TAG_COUNT,SortDirection.DESCENDING).addSort(Keys.OBJECT_ID,SortDirection.DESCENDING).setPage(1,Integer.MAX_VALUE).setPageCount(1);
  try {
    final List<JSONObject> ret=CollectionUtils.jsonArrayToList(domainRepository.get(query).optJSONArray(Keys.RESULTS));
    for (    final JSONObject domain : ret) {
      final List<JSONObject> tags=getTags(domain.optString(Keys.OBJECT_ID));
      domain.put(Domain.DOMAIN_T_TAGS,(Object)tags);
    }
    return ret;
  }
 catch (  final Exception e) {
    LOGGER.log(Level.ERROR,"Gets all domains failed",e);
    return Collections.emptyList();
  }
}
