/** 
 * Removes a follow relationship.
 * @param followerId    the specified follower id
 * @param followingId   the specified following entity id
 * @param followingType the specified following type
 * @throws RepositoryException repository exception
 */
public synchronized void unfollow(final String followerId,final String followingId,final int followingType) throws RepositoryException {
  followRepository.removeByFollowerIdAndFollowingId(followerId,followingId,followingType);
  if (Follow.FOLLOWING_TYPE_C_TAG == followingType) {
    final JSONObject tag=tagRepository.get(followingId);
    if (null == tag) {
      LOGGER.log(Level.ERROR,"Not found tag [id={0}] to unfollow",followingId);
      return;
    }
    tag.put(Tag.TAG_FOLLOWER_CNT,tag.optInt(Tag.TAG_FOLLOWER_CNT) - 1);
    if (tag.optInt(Tag.TAG_FOLLOWER_CNT) < 0) {
      tag.put(Tag.TAG_FOLLOWER_CNT,0);
    }
    tag.put(Tag.TAG_RANDOM_DOUBLE,Math.random());
    tagRepository.update(followingId,tag);
  }
 else   if (Follow.FOLLOWING_TYPE_C_ARTICLE == followingType) {
    final JSONObject article=articleRepository.get(followingId);
    if (null == article) {
      LOGGER.log(Level.ERROR,"Not found article [id={0}] to unfollow",followingId);
      return;
    }
    article.put(Article.ARTICLE_COLLECT_CNT,article.optInt(Article.ARTICLE_COLLECT_CNT) - 1);
    if (article.optInt(Article.ARTICLE_COLLECT_CNT) < 0) {
      article.put(Article.ARTICLE_COLLECT_CNT,0);
    }
    articleRepository.update(followingId,article,Article.ARTICLE_COLLECT_CNT);
  }
 else   if (Follow.FOLLOWING_TYPE_C_ARTICLE_WATCH == followingType) {
    final JSONObject article=articleRepository.get(followingId);
    if (null == article) {
      LOGGER.log(Level.ERROR,"Not found article [id={0}] to unwatch",followingId);
      return;
    }
    article.put(Article.ARTICLE_WATCH_CNT,article.optInt(Article.ARTICLE_WATCH_CNT) - 1);
    if (article.optInt(Article.ARTICLE_WATCH_CNT) < 0) {
      article.put(Article.ARTICLE_WATCH_CNT,0);
    }
    articleRepository.update(followingId,article,Article.ARTICLE_WATCH_CNT);
  }
}
