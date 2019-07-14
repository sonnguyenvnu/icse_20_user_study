/** 
 * Normalizes the specified title. For example, Normalizes "JS" to "JavaScript.
 * @param title the specified title
 * @return normalized title
 */
private static String normalize(final String title){
  final TagCache cache=BeanManager.getInstance().getReference(TagCache.class);
  final List<JSONObject> iconTags=cache.getIconTags(Integer.MAX_VALUE);
  Collections.sort(iconTags,(t1,t2) -> {
    final String u1Title=t1.optString(Tag.TAG_T_TITLE_LOWER_CASE);
    final String u2Title=t2.optString(Tag.TAG_T_TITLE_LOWER_CASE);
    return u2Title.length() - u1Title.length();
  }
);
  for (  final JSONObject iconTag : iconTags) {
    final String iconTagTitle=iconTag.optString(Tag.TAG_TITLE);
    if (iconTagTitle.length() < 2) {
      break;
    }
    if (StringUtils.containsIgnoreCase(title,iconTagTitle)) {
      return iconTagTitle;
    }
  }
  final List<JSONObject> allTags=cache.getTags();
  Collections.sort(allTags,(t1,t2) -> {
    final String u1Title=t1.optString(Tag.TAG_T_TITLE_LOWER_CASE);
    final String u2Title=t2.optString(Tag.TAG_T_TITLE_LOWER_CASE);
    return u2Title.length() - u1Title.length();
  }
);
  for (  final Map.Entry<String,Set<String>> entry : NORMALIZE_MAPPINGS.entrySet()) {
    final Set<String> oddTitles=entry.getValue();
    for (    final String oddTitle : oddTitles) {
      if (StringUtils.equalsIgnoreCase(title,oddTitle)) {
        return entry.getKey();
      }
    }
  }
  for (  final JSONObject tag : allTags) {
    final String tagTitle=tag.optString(Tag.TAG_TITLE);
    final String tagURI=tag.optString(Tag.TAG_URI);
    if (StringUtils.equalsIgnoreCase(title,tagURI) || StringUtils.equalsIgnoreCase(title,tagTitle)) {
      return tag.optString(Tag.TAG_TITLE);
    }
  }
  return title;
}
