/** 
 * The specified follower watches the specified following article.
 * @param followerId         the specified follower id
 * @param followingArticleId the specified following article id
 */
@Transactional public void watchArticle(final String followerId,final String followingArticleId){
  try {
    follow(followerId,followingArticleId,Follow.FOLLOWING_TYPE_C_ARTICLE_WATCH);
  }
 catch (  final RepositoryException e) {
    final String msg="User[id=" + followerId + "] watches an article[id=" + followingArticleId + "] failed";
    LOGGER.log(Level.ERROR,msg,e);
  }
}
