/** 
 * Fills relevant articles.
 * @param dataModel the specified data model
 * @param article   the specified article
 * @throws Exception exception
 */
public void fillRelevantArticles(final Map<String,Object> dataModel,final JSONObject article){
  final int articleStatus=article.optInt(Article.ARTICLE_STATUS);
  if (Article.ARTICLE_STATUS_C_INVALID == articleStatus) {
    dataModel.put(Common.SIDE_RELEVANT_ARTICLES,Collections.emptyList());
    return;
  }
  Stopwatchs.start("Fills relevant articles");
  try {
    dataModel.put(Common.SIDE_RELEVANT_ARTICLES,articleQueryService.getRelevantArticles(article,Symphonys.SIDE_RELEVANT_ARTICLES_CNT));
  }
  finally {
    Stopwatchs.end();
  }
}
