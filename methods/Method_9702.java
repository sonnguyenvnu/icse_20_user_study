/** 
 * Gets a tag by the specified tag id.
 * @param id the specified tag id
 * @return tag, returns {@code null} if not found
 */
public JSONObject getTag(final String id){
  final JSONObject tag=CACHE.get(id);
  if (null == tag) {
    return null;
  }
  final JSONObject ret=JSONs.clone(tag);
  TITLE_URIS.put(ret.optString(Tag.TAG_TITLE),ret.optString(Tag.TAG_URI));
  return ret;
}
