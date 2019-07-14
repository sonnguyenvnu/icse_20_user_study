/** 
 * Admin cancels stick an article specified by the given article id.
 * @param articleId the given article id
 */
@Transactional public synchronized void adminCancelStick(final String articleId){
  try {
    final JSONObject article=articleRepository.get(articleId);
    if (null == article) {
      return;
    }
    article.put(Article.ARTICLE_STICK,0L);
    articleRepository.update(articleId,article,Article.ARTICLE_STICK);
  }
 catch (  final RepositoryException e) {
    LOGGER.log(Level.ERROR,"Admin cancel sticks an article[id=" + articleId + "] failed",e);
  }
}
