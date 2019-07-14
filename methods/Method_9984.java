/** 
 * Accepts a comment specified with the given comment id.
 * @param commentId
 * @throws ServiceException service exception
 */
public void acceptComment(final String commentId) throws ServiceException {
  try {
    final JSONObject comment=commentRepository.get(commentId);
    final String articleId=comment.optString(Comment.COMMENT_ON_ARTICLE_ID);
    final Query query=new Query().setFilter(new PropertyFilter(Comment.COMMENT_ON_ARTICLE_ID,FilterOperator.EQUAL,articleId));
    final List<JSONObject> comments=CollectionUtils.jsonArrayToList(commentRepository.get(query).optJSONArray(Keys.RESULTS));
    for (    final JSONObject c : comments) {
      final int offered=c.optInt(Comment.COMMENT_QNA_OFFERED);
      if (Comment.COMMENT_QNA_OFFERED_C_YES == offered) {
        return;
      }
    }
    final String rewardId=Ids.genTimeMillisId();
    final JSONObject article=articleRepository.get(articleId);
    final String articleAuthorId=article.optString(Article.ARTICLE_AUTHOR_ID);
    final String commentAuthorId=comment.optString(Comment.COMMENT_AUTHOR_ID);
    final int offerPoint=article.optInt(Article.ARTICLE_QNA_OFFER_POINT);
    if (Comment.COMMENT_ANONYMOUS_C_PUBLIC == comment.optInt(Comment.COMMENT_ANONYMOUS)) {
      final boolean succ=null != pointtransferMgmtService.transfer(articleAuthorId,commentAuthorId,Pointtransfer.TRANSFER_TYPE_C_QNA_OFFER,offerPoint,rewardId,System.currentTimeMillis(),"");
      if (!succ) {
        throw new ServiceException(langPropsService.get("transferFailLabel"));
      }
    }
    comment.put(Comment.COMMENT_QNA_OFFERED,Comment.COMMENT_QNA_OFFERED_C_YES);
    final Transaction transaction=commentRepository.beginTransaction();
    commentRepository.update(commentId,comment);
    transaction.commit();
    final JSONObject reward=new JSONObject();
    reward.put(Keys.OBJECT_ID,rewardId);
    reward.put(Reward.SENDER_ID,articleAuthorId);
    reward.put(Reward.DATA_ID,articleId);
    reward.put(Reward.TYPE,Reward.TYPE_C_ACCEPT_COMMENT);
    rewardMgmtService.addReward(reward);
    final JSONObject notification=new JSONObject();
    notification.put(Notification.NOTIFICATION_USER_ID,commentAuthorId);
    notification.put(Notification.NOTIFICATION_DATA_ID,rewardId);
    notificationMgmtService.addCommentAcceptNotification(notification);
    livenessMgmtService.incLiveness(articleAuthorId,Liveness.LIVENESS_ACCEPT_ANSWER);
  }
 catch (  final ServiceException e) {
    throw e;
  }
catch (  final Exception e) {
    LOGGER.log(Level.ERROR,"Accepts a comment [id=" + commentId + "] failed",e);
    throw new ServiceException(langPropsService.get("systemErrLabel"));
  }
}
