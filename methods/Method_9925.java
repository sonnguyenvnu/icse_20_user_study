/** 
 * Increments the view count of the specified article by the given visit.
 * @param visit the given visit
 */
public void incArticleViewCount(final JSONObject visit){
  Symphonys.EXECUTOR_SERVICE.submit(() -> {
    final String visitURL=visit.optString(Visit.VISIT_URL);
    final String articleId=StringUtils.substringAfter(visitURL,"/article/");
    boolean visitedB4=false;
    try {
      if ("1".equals(optionRepository.get(Option.ID_C_MISC_ARTICLE_VISIT_COUNT_MODE).optString(Option.OPTION_VALUE))) {
        visitedB4=visitMgmtService.add(visit);
      }
    }
 catch (    final Exception e) {
      LOGGER.log(Level.ERROR,"Gets visit count mode failed",e);
    }
    if (visitedB4) {
      return;
    }
    final Transaction transaction=articleRepository.beginTransaction();
    try {
      final JSONObject article=articleRepository.get(articleId);
      if (null == article) {
        if (transaction.isActive()) {
          transaction.rollback();
        }
        return;
      }
      final int viewCnt=article.optInt(Article.ARTICLE_VIEW_CNT);
      article.put(Article.ARTICLE_VIEW_CNT,viewCnt + 1);
      article.put(Article.ARTICLE_RANDOM_DOUBLE,Math.random());
      articleRepository.update(articleId,article,Article.ARTICLE_VIEW_CNT,Article.ARTICLE_RANDOM_DOUBLE);
      transaction.commit();
    }
 catch (    final RepositoryException e) {
      if (transaction.isActive()) {
        transaction.rollback();
      }
      LOGGER.log(Level.ERROR,"Incs an article view count failed",e);
    }
  }
);
}
