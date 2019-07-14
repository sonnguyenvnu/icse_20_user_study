/** 
 * Fills the description for the specified tag.
 * @param tag the specified tag
 */
public static void fillDescription(final JSONObject tag){
  if (null == tag) {
    return;
  }
  String description=tag.optString(Tag.TAG_DESCRIPTION);
  String descriptionText=tag.optString(Tag.TAG_TITLE);
  if (StringUtils.isNotBlank(description)) {
    description=Emotions.convert(description);
    description=Markdowns.toHTML(description);
    tag.put(Tag.TAG_DESCRIPTION,description);
    descriptionText=Jsoup.parse(description).text();
  }
  tag.put(Tag.TAG_T_DESCRIPTION_TEXT,descriptionText);
}
