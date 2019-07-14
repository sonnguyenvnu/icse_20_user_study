/** 
 * Gets the index perfect articles.
 * @return hot articles, returns an empty list if not found
 */
public List<JSONObject> getIndexPerfectArticles(){
  return articleCache.getPerfectArticles();
}
