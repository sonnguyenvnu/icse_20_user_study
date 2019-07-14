/** 
 * Adds or updates the specified tag.
 * @param tag the specified tag
 */
public void putTag(final JSONObject tag){
  CACHE.put(tag.optString(Keys.OBJECT_ID),JSONs.clone(tag));
  TITLE_URIS.put(tag.optString(Tag.TAG_TITLE),tag.optString(Tag.TAG_URI));
}
