/** 
 * Gets a domain by the specified id.
 * @param domainId the specified id
 * @return a domain, return {@code null} if not found
 */
public JSONObject getDomain(final String domainId){
  try {
    final JSONObject ret=domainRepository.get(domainId);
    final List<JSONObject> tags=getTags(domainId);
    ret.put(Domain.DOMAIN_T_TAGS,(Object)tags);
    return ret;
  }
 catch (  final RepositoryException e) {
    LOGGER.log(Level.ERROR,"Gets a domain [domainId=" + domainId + "] failed",e);
    return null;
  }
}
