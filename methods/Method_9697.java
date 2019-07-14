/** 
 * Gets perfect articles.
 * @return side random articles
 */
public List<JSONObject> getPerfectArticles(){
  if (PERFECT_ARTICLES.isEmpty()) {
    return Collections.emptyList();
  }
  return JSONs.clone(PERFECT_ARTICLES);
}
