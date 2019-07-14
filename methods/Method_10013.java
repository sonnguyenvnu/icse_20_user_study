/** 
 * Adds a domain relation.
 * @param domain the specified domain relation
 * @return domain id
 * @throws ServiceException service exception
 */
@Transactional public String addDomain(final JSONObject domain) throws ServiceException {
  try {
    final JSONObject record=new JSONObject();
    record.put(Domain.DOMAIN_CSS,domain.optString(Domain.DOMAIN_CSS));
    record.put(Domain.DOMAIN_DESCRIPTION,domain.optString(Domain.DOMAIN_DESCRIPTION));
    record.put(Domain.DOMAIN_ICON_PATH,domain.optString(Domain.DOMAIN_ICON_PATH));
    record.put(Domain.DOMAIN_SEO_DESC,domain.optString(Domain.DOMAIN_SEO_DESC));
    record.put(Domain.DOMAIN_SEO_KEYWORDS,domain.optString(Domain.DOMAIN_SEO_KEYWORDS));
    record.put(Domain.DOMAIN_SEO_TITLE,domain.optString(Domain.DOMAIN_SEO_TITLE));
    record.put(Domain.DOMAIN_STATUS,domain.optInt(Domain.DOMAIN_STATUS));
    record.put(Domain.DOMAIN_TITLE,domain.optString(Domain.DOMAIN_TITLE));
    record.put(Domain.DOMAIN_URI,domain.optString(Domain.DOMAIN_URI));
    record.put(Domain.DOMAIN_TAG_COUNT,0);
    record.put(Domain.DOMAIN_TYPE,"");
    record.put(Domain.DOMAIN_SORT,10);
    record.put(Domain.DOMAIN_NAV,Domain.DOMAIN_NAV_C_ENABLED);
    final JSONObject domainCntOption=optionRepository.get(Option.ID_C_STATISTIC_DOMAIN_COUNT);
    final int domainCnt=domainCntOption.optInt(Option.OPTION_VALUE);
    domainCntOption.put(Option.OPTION_VALUE,domainCnt + 1);
    optionRepository.update(Option.ID_C_STATISTIC_DOMAIN_COUNT,domainCntOption);
    final String ret=domainRepository.add(record);
    domainCache.loadDomains();
    return ret;
  }
 catch (  final RepositoryException e) {
    LOGGER.log(Level.ERROR,"Adds a domain failed",e);
    throw new ServiceException(e);
  }
}
