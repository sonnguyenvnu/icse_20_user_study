/** 
 * Gets a tag by the specified tag title.
 * @param tagTitle the specified tag title
 * @return a tag, {@code null} if not found
 * @throws RepositoryException repository exception
 */
public JSONObject getByTitle(final String tagTitle) throws RepositoryException {
  final Query query=new Query().setFilter(new PropertyFilter(Tag.TAG_TITLE,FilterOperator.EQUAL,tagTitle)).setPageCount(1);
  final JSONObject result=get(query);
  final JSONArray array=result.optJSONArray(Keys.RESULTS);
  if (0 == array.length()) {
    return null;
  }
  return array.optJSONObject(0);
}
