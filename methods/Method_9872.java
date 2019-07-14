/** 
 * Gets an article by the specified article title.
 * @param articleTitle the specified article title
 * @return an article, {@code null} if not found
 * @throws RepositoryException repository exception
 */
public JSONObject getByTitle(final String articleTitle) throws RepositoryException {
  final Query query=new Query().setFilter(new PropertyFilter(Article.ARTICLE_TITLE,FilterOperator.EQUAL,articleTitle)).setPageCount(1);
  final JSONObject result=get(query);
  final JSONArray array=result.optJSONArray(Keys.RESULTS);
  if (0 == array.length()) {
    return null;
  }
  return array.optJSONObject(0);
}
