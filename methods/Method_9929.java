/** 
 * Sticks an article specified by the given article id.
 * @param articleId the given article id
 * @throws ServiceException service exception
 */
public synchronized void stick(final String articleId) throws ServiceException {
  final Transaction transaction=articleRepository.beginTransaction();
  try {
    final JSONObject article=articleRepository.get(articleId);
    if (null == article) {
      return;
    }
    final String authorId=article.optString(Article.ARTICLE_AUTHOR_ID);
    final JSONObject author=userRepository.get(authorId);
    final int balance=author.optInt(UserExt.USER_POINT);
    if (balance - Pointtransfer.TRANSFER_SUM_C_STICK_ARTICLE < 0) {
      throw new ServiceException(langPropsService.get("insufficientBalanceLabel"));
    }
    final Query query=new Query().setFilter(new PropertyFilter(Article.ARTICLE_STICK,FilterOperator.GREATER_THAN,0L));
    final JSONArray articles=articleRepository.get(query).optJSONArray(Keys.RESULTS);
    if (articles.length() > 1) {
      final Set<String> ids=new HashSet<>();
      for (int i=0; i < articles.length(); i++) {
        ids.add(articles.optJSONObject(i).optString(Keys.OBJECT_ID));
      }
      if (!ids.contains(articleId)) {
        throw new ServiceException(langPropsService.get("stickExistLabel"));
      }
    }
    article.put(Article.ARTICLE_STICK,System.currentTimeMillis());
    articleRepository.update(articleId,article,Article.ARTICLE_STICK);
    transaction.commit();
    final boolean succ=null != pointtransferMgmtService.transfer(article.optString(Article.ARTICLE_AUTHOR_ID),Pointtransfer.ID_C_SYS,Pointtransfer.TRANSFER_TYPE_C_STICK_ARTICLE,Pointtransfer.TRANSFER_SUM_C_STICK_ARTICLE,articleId,System.currentTimeMillis(),"");
    if (!succ) {
      throw new ServiceException(langPropsService.get("stickFailedLabel"));
    }
  }
 catch (  final RepositoryException e) {
    if (transaction.isActive()) {
      transaction.rollback();
    }
    LOGGER.log(Level.ERROR,"Sticks an article[id=" + articleId + "] failed",e);
    throw new ServiceException(langPropsService.get("stickFailedLabel"));
  }
}
