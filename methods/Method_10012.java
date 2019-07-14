/** 
 * Removes a domain-tag relation.
 * @param domainId the specified domain id
 * @param tagId    the specified tag id
 */
@Transactional public void removeDomainTag(final String domainId,final String tagId){
  try {
    final JSONObject domain=domainRepository.get(domainId);
    domain.put(Domain.DOMAIN_TAG_COUNT,domain.optInt(Domain.DOMAIN_TAG_COUNT) - 1);
    domainRepository.update(domainId,domain);
    final Query query=new Query().setFilter(CompositeFilterOperator.and(new PropertyFilter(Domain.DOMAIN + "_" + Keys.OBJECT_ID,FilterOperator.EQUAL,domainId),new PropertyFilter(Tag.TAG + "_" + Keys.OBJECT_ID,FilterOperator.EQUAL,tagId)));
    final JSONArray relations=domainTagRepository.get(query).optJSONArray(Keys.RESULTS);
    if (relations.length() < 1) {
      return;
    }
    final JSONObject relation=relations.optJSONObject(0);
    domainTagRepository.remove(relation.optString(Keys.OBJECT_ID));
    domainCache.loadDomains();
  }
 catch (  final RepositoryException e) {
    LOGGER.log(Level.ERROR,"Adds a domain-tag relation failed",e);
  }
}
