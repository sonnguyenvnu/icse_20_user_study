/** 
 * Determines whether the specified data dose belong to the specified user.
 * @param userId   the specified user id
 * @param dataId   the specified data id
 * @param dataType the specified data type
 * @return {@code true} if it belongs to the user, otherwise returns {@code false}
 */
public boolean isOwn(final String userId,final String dataId,final int dataType){
  try {
    if (Vote.DATA_TYPE_C_ARTICLE == dataType) {
      final JSONObject article=articleRepository.get(dataId);
      if (null == article) {
        LOGGER.log(Level.ERROR,"Not found article [id={0}]",dataId);
        return false;
      }
      return article.optString(Article.ARTICLE_AUTHOR_ID).equals(userId);
    }
 else     if (Vote.DATA_TYPE_C_COMMENT == dataType) {
      final JSONObject comment=commentRepository.get(dataId);
      if (null == comment) {
        LOGGER.log(Level.ERROR,"Not found comment [id={0}]",dataId);
        return false;
      }
      return comment.optString(Comment.COMMENT_AUTHOR_ID).equals(userId);
    }
    return false;
  }
 catch (  final RepositoryException e) {
    LOGGER.log(Level.ERROR,e.getMessage());
    return false;
  }
}
