/** 
 * Generates participants for the specified articles.
 * @param articles        the specified articles
 * @param participantsCnt the specified generate size
 */
public void genParticipants(final List<JSONObject> articles,final Integer participantsCnt){
  Stopwatchs.start("Generates participants");
  try {
    for (    final JSONObject article : articles) {
      article.put(Article.ARTICLE_T_PARTICIPANTS,(Object)Collections.emptyList());
      if (article.optInt(Article.ARTICLE_COMMENT_CNT) < 1) {
        continue;
      }
      final List<JSONObject> articleParticipants=getArticleLatestParticipants(article.optString(Keys.OBJECT_ID),participantsCnt);
      article.put(Article.ARTICLE_T_PARTICIPANTS,(Object)articleParticipants);
    }
  }
  finally {
    Stopwatchs.end();
  }
}
