/** 
 * Whether a tag specified by the given tag title in a domain specified by the given domain id.
 * @param tagTitle the given tag title
 * @param domainId the given domain id
 * @return {@code true} if the tag in the domain, returns {@code false} otherwise
 */
public boolean containTag(final String tagTitle,final String domainId){
  try {
    final JSONObject domain=domainRepository.get(domainId);
    if (null == domain) {
      return true;
    }
    final JSONObject tag=tagRepository.getByTitle(tagTitle);
    if (null == tag) {
      return true;
    }
    final Query query=new Query().setFilter(CompositeFilterOperator.and(new PropertyFilter(Domain.DOMAIN + "_" + Keys.OBJECT_ID,FilterOperator.EQUAL,domainId),new PropertyFilter(Tag.TAG + "_" + Keys.OBJECT_ID,FilterOperator.EQUAL,tag.optString(Keys.OBJECT_ID))));
    return domainTagRepository.count(query) > 0;
  }
 catch (  final Exception e) {
    LOGGER.log(Level.ERROR,"Check domain tag [tagTitle=" + tagTitle + ", domainId=" + domainId + "] failed",e);
    return true;
  }
}
