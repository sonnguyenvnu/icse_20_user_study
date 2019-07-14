/** 
 * Gets domains of the specified tag belongs to.
 * @param tagTitle the specified tag title
 * @return domains, returns an empty list if not found
 */
public List<JSONObject> getDomains(final String tagTitle){
  final List<JSONObject> ret=new ArrayList<>();
  try {
    final JSONObject tag=tagRepository.getByTitle(tagTitle);
    if (null == tag) {
      return ret;
    }
    final String tagId=tag.optString(Keys.OBJECT_ID);
    final JSONArray relations=domainTagRepository.getByTagId(tagId,1,Integer.MAX_VALUE).optJSONArray(Keys.RESULTS);
    if (1 > relations.length()) {
      return ret;
    }
    final List<String> domainIds=new ArrayList<>();
    for (int i=0; i < relations.length(); i++) {
      final JSONObject relation=relations.optJSONObject(i);
      final String domainId=relation.optString(Domain.DOMAIN + "_" + Keys.OBJECT_ID);
      domainIds.add(domainId);
    }
    Collections.sort(domainIds);
    for (    final String domainId : domainIds) {
      final JSONObject domain=domainRepository.get(domainId);
      ret.add(domain);
    }
  }
 catch (  final Exception e) {
    LOGGER.log(Level.ERROR,"Gets domains of tag [title=" + tagTitle + "] failed",e);
  }
  return ret;
}
