/** 
 * Gets side random articles.
 * @return side random articles
 */
public List<JSONObject> getSideRandomArticles(){
  int size=Symphonys.SIDE_RANDOM_ARTICLES_CNT;
  if (1 > size) {
    return Collections.emptyList();
  }
  if (SIDE_RANDOM_ARTICLES.isEmpty()) {
    return Collections.emptyList();
  }
  size=size > SIDE_RANDOM_ARTICLES.size() ? SIDE_RANDOM_ARTICLES.size() : size;
  Collections.shuffle(SIDE_RANDOM_ARTICLES);
  return JSONs.clone(SIDE_RANDOM_ARTICLES.subList(0,size));
}
