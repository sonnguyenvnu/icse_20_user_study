/** 
 * Gets an article abstract by the specified article id.
 * @param articleId the specified article id
 * @return article abstract, return {@code null} if not found
 */
public String getArticleAbstract(final String articleId){
  final JSONObject value=ARTICLE_ABSTRACT_CACHE.get(articleId);
  if (null == value) {
    return null;
  }
  return value.optString(Common.DATA);
}
