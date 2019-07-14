/** 
 * Gets the tags the specified fetch size.
 * @param fetchSize the specified fetch size
 * @return tags, returns an empty list if not found
 */
public List<JSONObject> getTags(final int fetchSize){
  return tagCache.getIconTags(fetchSize);
}
