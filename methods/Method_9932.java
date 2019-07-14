/** 
 * Expires sticked articles.
 * @throws ServiceException service exception
 */
@Transactional public void expireStick() throws ServiceException {
  try {
    final Query query=new Query().setFilter(new PropertyFilter(Article.ARTICLE_STICK,FilterOperator.GREATER_THAN,0L));
    final JSONArray articles=articleRepository.get(query).optJSONArray(Keys.RESULTS);
    if (articles.length() < 1) {
      return;
    }
    final long stepTime=Symphonys.STICK_ARTICLE_TIME;
    final long now=System.currentTimeMillis();
    for (int i=0; i < articles.length(); i++) {
      final JSONObject article=articles.optJSONObject(i);
      final long stick=article.optLong(Article.ARTICLE_STICK);
      if (stick >= Long.MAX_VALUE) {
        continue;
      }
      final long expired=stick + stepTime;
      if (expired < now) {
        article.put(Article.ARTICLE_STICK,0L);
        articleRepository.update(article.optString(Keys.OBJECT_ID),article,Article.ARTICLE_STICK);
      }
    }
  }
 catch (  final RepositoryException e) {
    LOGGER.log(Level.ERROR,"Expires sticked articles failed",e);
    throw new ServiceException();
  }
}
