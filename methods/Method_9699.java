/** 
 * Adds or updates the specified article.
 * @param article the specified article
 */
public void putArticle(final JSONObject article){
  final String articleId=article.optString(Keys.OBJECT_ID);
  ARTICLE_CACHE.put(articleId,JSONs.clone(article));
  ARTICLE_ABSTRACT_CACHE.remove(articleId);
}
