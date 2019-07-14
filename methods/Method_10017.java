/** 
 * Gets a domain by the specified domain URI.
 * @param domainURI the specified domain URI
 * @return domain, returns {@code null} if not null
 */
public JSONObject getByURI(final String domainURI){
  try {
    final JSONObject ret=domainRepository.getByURI(domainURI);
    if (null == ret) {
      return null;
    }
    if (Domain.DOMAIN_STATUS_C_VALID != ret.optInt(Domain.DOMAIN_STATUS)) {
      return null;
    }
    String description=ret.optString(Domain.DOMAIN_DESCRIPTION);
    String descriptionText=ret.optString(Domain.DOMAIN_TITLE);
    if (StringUtils.isNotBlank(description)) {
      description=Markdowns.toHTML(description);
      ret.put(Domain.DOMAIN_DESCRIPTION,description);
      descriptionText=Jsoup.parse(description).text();
    }
    final String domainTitle=ret.optString(Domain.DOMAIN_TITLE);
    if (StringUtils.isBlank(ret.optString(Domain.DOMAIN_SEO_TITLE))) {
      ret.put(Domain.DOMAIN_SEO_TITLE,domainTitle);
    }
    if (StringUtils.isBlank(ret.optString(Domain.DOMAIN_SEO_DESC))) {
      ret.put(Domain.DOMAIN_SEO_DESC,descriptionText);
    }
    if (StringUtils.isBlank(ret.optString(Domain.DOMAIN_SEO_KEYWORDS))) {
      ret.put(Domain.DOMAIN_SEO_KEYWORDS,domainTitle);
    }
    return ret;
  }
 catch (  final RepositoryException e) {
    LOGGER.log(Level.ERROR,"Gets domain [URI=" + domainURI + "] failed",e);
    return null;
  }
}
