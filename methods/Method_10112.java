/** 
 * Gets a tag by the specified tag title.
 * @param tagTitle the specified tag title
 * @return tag, returns {@code null} if not null
 */
public JSONObject getTagByTitle(final String tagTitle){
  try {
    final JSONObject ret=tagRepository.getByTitle(tagTitle);
    if (null == ret) {
      return null;
    }
    if (Tag.TAG_STATUS_C_VALID != ret.optInt(Tag.TAG_STATUS)) {
      return null;
    }
    Tag.fillDescription(ret);
    if (StringUtils.isBlank(ret.optString(Tag.TAG_SEO_TITLE))) {
      ret.put(Tag.TAG_SEO_TITLE,tagTitle);
    }
    if (StringUtils.isBlank(ret.optString(Tag.TAG_SEO_DESC))) {
      ret.put(Tag.TAG_SEO_DESC,ret.optString(Tag.TAG_T_DESCRIPTION_TEXT));
    }
    if (StringUtils.isBlank(ret.optString(Tag.TAG_SEO_KEYWORDS))) {
      ret.put(Tag.TAG_SEO_KEYWORDS,tagTitle);
    }
    final List<JSONObject> domains=getDomains(tagTitle);
    ret.put(Tag.TAG_T_DOMAINS,(Object)domains);
    return ret;
  }
 catch (  final RepositoryException e) {
    LOGGER.log(Level.ERROR,"Gets tag [title=" + tagTitle + "] failed",e);
    return null;
  }
}
