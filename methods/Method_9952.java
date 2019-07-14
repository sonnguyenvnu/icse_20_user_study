/** 
 * Generates the specified article author name and thumbnail URL.
 * @param article the specified article
 * @throws RepositoryException repository exception
 */
private void genArticleAuthor(final JSONObject article) throws RepositoryException {
  final String authorId=article.optString(Article.ARTICLE_AUTHOR_ID);
  final JSONObject author=userRepository.get(authorId);
  article.put(Article.ARTICLE_T_AUTHOR,author);
  if (Article.ARTICLE_ANONYMOUS_C_ANONYMOUS == article.optInt(Article.ARTICLE_ANONYMOUS)) {
    article.put(Article.ARTICLE_T_AUTHOR_NAME,UserExt.ANONYMOUS_USER_NAME);
    article.put(Article.ARTICLE_T_AUTHOR_THUMBNAIL_URL + "210",avatarQueryService.getDefaultAvatarURL("210"));
    article.put(Article.ARTICLE_T_AUTHOR_THUMBNAIL_URL + "48",avatarQueryService.getDefaultAvatarURL("48"));
    article.put(Article.ARTICLE_T_AUTHOR_THUMBNAIL_URL + "20",avatarQueryService.getDefaultAvatarURL("20"));
  }
 else {
    article.put(Article.ARTICLE_T_AUTHOR_NAME,author.optString(User.USER_NAME));
    article.put(Article.ARTICLE_T_AUTHOR_THUMBNAIL_URL + "210",avatarQueryService.getAvatarURLByUser(author,"210"));
    article.put(Article.ARTICLE_T_AUTHOR_THUMBNAIL_URL + "48",avatarQueryService.getAvatarURLByUser(author,"48"));
    article.put(Article.ARTICLE_T_AUTHOR_THUMBNAIL_URL + "20",avatarQueryService.getAvatarURLByUser(author,"20"));
  }
}
