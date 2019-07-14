/** 
 * Gets a tag by the specified tag URI.
 * @param tagURI the specified tag URI
 * @return a tag, {@code null} if not found
 * @throws RepositoryException repository exception
 */
public JSONObject getByURI(final String tagURI) throws RepositoryException {
  final String uri=URLs.encode(tagURI);
  final Query query=new Query().setFilter(new PropertyFilter(Tag.TAG_URI,FilterOperator.EQUAL,uri)).addSort(Tag.TAG_REFERENCE_CNT,SortDirection.DESCENDING).setPageCount(1);
  final JSONObject result=get(query);
  final JSONArray array=result.optJSONArray(Keys.RESULTS);
  if (0 == array.length()) {
    return null;
  }
  return array.optJSONObject(0);
}
