/** 
 * A user specified by the given sender id rewards the author of an article specified by the given article id.
 * @param articleId the given article id
 * @param senderId  the given sender id
 * @throws ServiceException service exception
 */
public void reward(final String articleId,final String senderId) throws ServiceException {
  try {
    final JSONObject article=articleRepository.get(articleId);
    if (null == article) {
      return;
    }
    if (Article.ARTICLE_STATUS_C_INVALID == article.optInt(Article.ARTICLE_STATUS)) {
      return;
    }
    final JSONObject sender=userRepository.get(senderId);
    if (null == sender) {
      return;
    }
    if (UserExt.USER_STATUS_C_VALID != sender.optInt(UserExt.USER_STATUS)) {
      return;
    }
    final String receiverId=article.optString(Article.ARTICLE_AUTHOR_ID);
    final JSONObject receiver=userRepository.get(receiverId);
    if (null == receiver) {
      return;
    }
    if (UserExt.USER_STATUS_C_VALID != receiver.optInt(UserExt.USER_STATUS)) {
      return;
    }
    if (receiverId.equals(senderId)) {
      return;
    }
    final int rewardPoint=article.optInt(Article.ARTICLE_REWARD_POINT);
    if (rewardPoint < 1) {
      return;
    }
    if (rewardQueryService.isRewarded(senderId,articleId,Reward.TYPE_C_ARTICLE)) {
      return;
    }
    final String rewardId=Ids.genTimeMillisId();
    if (Article.ARTICLE_ANONYMOUS_C_PUBLIC == article.optInt(Article.ARTICLE_ANONYMOUS)) {
      final boolean succ=null != pointtransferMgmtService.transfer(senderId,receiverId,Pointtransfer.TRANSFER_TYPE_C_ARTICLE_REWARD,rewardPoint,rewardId,System.currentTimeMillis(),"");
      if (!succ) {
        throw new ServiceException();
      }
    }
    final JSONObject reward=new JSONObject();
    reward.put(Keys.OBJECT_ID,rewardId);
    reward.put(Reward.SENDER_ID,senderId);
    reward.put(Reward.DATA_ID,articleId);
    reward.put(Reward.TYPE,Reward.TYPE_C_ARTICLE);
    rewardMgmtService.addReward(reward);
    final JSONObject notification=new JSONObject();
    notification.put(Notification.NOTIFICATION_USER_ID,receiverId);
    notification.put(Notification.NOTIFICATION_DATA_ID,rewardId);
    notificationMgmtService.addArticleRewardNotification(notification);
    livenessMgmtService.incLiveness(senderId,Liveness.LIVENESS_REWARD);
  }
 catch (  final RepositoryException e) {
    LOGGER.log(Level.ERROR,"Rewards an article[id=" + articleId + "] failed",e);
    throw new ServiceException(e);
  }
}
