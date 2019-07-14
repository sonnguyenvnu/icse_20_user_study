/** 
 * Gets a tag by the specified tag URI.
 * @param tagURI the specified tag URI
 * @return tag, returns {@code null} if not null
 */
public JSONObject getTagByURI(final String tagURI){
  try {
    final JSONObject ret=tagRepository.getByURI(tagURI);
    if (null == ret) {
      return null;
    }
    if (Tag.TAG_STATUS_C_VALID != ret.optInt(Tag.TAG_STATUS)) {
      return null;
    }
    Tag.fillDescription(ret);
    if (StringUtils.isBlank(ret.optString(Tag.TAG_SEO_TITLE))) {
      ret.put(Tag.TAG_SEO_TITLE,tagURI);
    }
    if (StringUtils.isBlank(ret.optString(Tag.TAG_SEO_DESC))) {
      ret.put(Tag.TAG_SEO_DESC,ret.optString(Tag.TAG_T_DESCRIPTION_TEXT));
    }
    if (StringUtils.isBlank(ret.optString(Tag.TAG_SEO_KEYWORDS))) {
      ret.put(Tag.TAG_SEO_KEYWORDS,tagURI);
    }
    final String tagTitle=ret.optString(Tag.TAG_TITLE);
    final List<JSONObject> domains=getDomains(tagTitle);
    ret.put(Tag.TAG_T_DOMAINS,(Object)domains);
    return ret;
  }
 catch (  final RepositoryException e) {
    LOGGER.log(Level.ERROR,"Gets tag [uri=" + tagURI + "] failed",e);
    return null;
  }
}
