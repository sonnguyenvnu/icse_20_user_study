/** 
 * Updates the specified domain by the given domain id.
 * @param domainId the given domain id
 * @param domain   the specified domain
 */
@Transactional public void updateDomain(final String domainId,final JSONObject domain){
  try {
    domainRepository.update(domainId,domain);
    domainCache.loadDomains();
  }
 catch (  final RepositoryException e) {
    LOGGER.log(Level.ERROR,"Updates a domain [id=" + domainId + "] failed",e);
  }
}
