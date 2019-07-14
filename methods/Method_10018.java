/** 
 * Gets a domain by the specified domain title.
 * @param domainTitle the specified domain title
 * @return domain, returns {@code null} if not null
 */
public JSONObject getByTitle(final String domainTitle){
  try {
    final JSONObject ret=domainRepository.getByTitle(domainTitle);
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
    LOGGER.log(Level.ERROR,"Gets domain [title=" + domainTitle + "] failed",e);
    return null;
  }
}
