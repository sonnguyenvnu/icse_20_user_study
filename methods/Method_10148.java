/** 
 * The specified user vote down the specified data entity with the specified data type.
 * @param userId   the specified user id
 * @param dataId   the specified data entity id
 * @param dataType the specified data type
 * @throws RepositoryException repository exception
 */
private void down(final String userId,final String dataId,final int dataType) throws RepositoryException {
  final int oldType=voteRepository.removeIfExists(userId,dataId,dataType);
  if (Vote.DATA_TYPE_C_ARTICLE == dataType) {
    final JSONObject article=articleRepository.get(dataId);
    if (null == article) {
      LOGGER.log(Level.ERROR,"Not found article [id={0}] to vote down",dataId);
      return;
    }
    if (-1 == oldType) {
      article.put(Article.ARTICLE_BAD_CNT,article.optInt(Article.ARTICLE_BAD_CNT) + 1);
    }
 else     if (Vote.TYPE_C_UP == oldType) {
      article.put(Article.ARTICLE_GOOD_CNT,article.optInt(Article.ARTICLE_GOOD_CNT) - 1);
      article.put(Article.ARTICLE_BAD_CNT,article.optInt(Article.ARTICLE_BAD_CNT) + 1);
    }
    final int ups=article.optInt(Article.ARTICLE_GOOD_CNT);
    final int downs=article.optInt(Article.ARTICLE_BAD_CNT);
    final long t=article.optLong(Keys.OBJECT_ID) / 1000;
    final double redditScore=redditArticleScore(ups,downs,t);
    article.put(Article.REDDIT_SCORE,redditScore);
    updateTagArticleScore(article);
    articleRepository.update(dataId,article,Article.ARTICLE_GOOD_CNT,Article.ARTICLE_BAD_CNT,Article.REDDIT_SCORE);
  }
 else   if (Vote.DATA_TYPE_C_COMMENT == dataType) {
    final JSONObject comment=commentRepository.get(dataId);
    if (null == comment) {
      LOGGER.log(Level.ERROR,"Not found comment [id={0}] to vote up",dataId);
      return;
    }
    if (-1 == oldType) {
      comment.put(Comment.COMMENT_BAD_CNT,comment.optInt(Comment.COMMENT_BAD_CNT) + 1);
    }
 else     if (Vote.TYPE_C_UP == oldType) {
      comment.put(Comment.COMMENT_GOOD_CNT,comment.optInt(Comment.COMMENT_GOOD_CNT) - 1);
      comment.put(Comment.COMMENT_BAD_CNT,comment.optInt(Comment.COMMENT_BAD_CNT) + 1);
    }
    final int ups=comment.optInt(Comment.COMMENT_GOOD_CNT);
    final int downs=comment.optInt(Comment.COMMENT_BAD_CNT);
    final double redditScore=redditCommentScore(ups,downs);
    comment.put(Comment.COMMENT_SCORE,redditScore);
    commentRepository.update(dataId,comment);
  }
 else {
    LOGGER.warn("Wrong data type [" + dataType + "]");
  }
  final JSONObject vote=new JSONObject();
  vote.put(Vote.USER_ID,userId);
  vote.put(Vote.DATA_ID,dataId);
  vote.put(Vote.TYPE,Vote.TYPE_C_DOWN);
  vote.put(Vote.DATA_TYPE,dataType);
  voteRepository.add(vote);
}
