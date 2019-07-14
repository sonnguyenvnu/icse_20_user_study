/** 
 * A user specified by the given sender id thanks the author of a comment specified by the given comment id.
 * @param commentId the given comment id
 * @param senderId  the given sender id
 * @throws ServiceException service exception
 */
public void thankComment(final String commentId,final String senderId) throws ServiceException {
  try {
    final JSONObject comment=commentRepository.get(commentId);
    if (null == comment) {
      return;
    }
    if (Comment.COMMENT_STATUS_C_INVALID == comment.optInt(Comment.COMMENT_STATUS)) {
      return;
    }
    final JSONObject sender=userRepository.get(senderId);
    if (null == sender) {
      return;
    }
    if (UserExt.USER_STATUS_C_VALID != sender.optInt(UserExt.USER_STATUS)) {
      return;
    }
    final String receiverId=comment.optString(Comment.COMMENT_AUTHOR_ID);
    final JSONObject receiver=userRepository.get(receiverId);
    if (null == receiver) {
      return;
    }
    if (UserExt.USER_STATUS_C_VALID != receiver.optInt(UserExt.USER_STATUS)) {
      return;
    }
    if (receiverId.equals(senderId)) {
      throw new ServiceException(langPropsService.get("thankSelfLabel"));
    }
    final int rewardPoint=Symphonys.POINT_THANK_COMMENT;
    if (rewardQueryService.isRewarded(senderId,commentId,Reward.TYPE_C_COMMENT)) {
      return;
    }
    final String rewardId=Ids.genTimeMillisId();
    if (Comment.COMMENT_ANONYMOUS_C_PUBLIC == comment.optInt(Comment.COMMENT_ANONYMOUS)) {
      final boolean succ=null != pointtransferMgmtService.transfer(senderId,receiverId,Pointtransfer.TRANSFER_TYPE_C_COMMENT_REWARD,rewardPoint,rewardId,System.currentTimeMillis(),"");
      if (!succ) {
        throw new ServiceException(langPropsService.get("transferFailLabel"));
      }
    }
    final int thankCnt=comment.optInt(Comment.COMMENT_THANK_CNT);
    comment.put(Comment.COMMENT_THANK_CNT,thankCnt + 1);
    final Transaction transaction=commentRepository.beginTransaction();
    commentRepository.update(commentId,comment);
    transaction.commit();
    final JSONObject reward=new JSONObject();
    reward.put(Keys.OBJECT_ID,rewardId);
    reward.put(Reward.SENDER_ID,senderId);
    reward.put(Reward.DATA_ID,commentId);
    reward.put(Reward.TYPE,Reward.TYPE_C_COMMENT);
    rewardMgmtService.addReward(reward);
    final JSONObject notification=new JSONObject();
    notification.put(Notification.NOTIFICATION_USER_ID,receiverId);
    notification.put(Notification.NOTIFICATION_DATA_ID,rewardId);
    notificationMgmtService.addCommentThankNotification(notification);
    livenessMgmtService.incLiveness(senderId,Liveness.LIVENESS_THANK);
  }
 catch (  final RepositoryException e) {
    LOGGER.log(Level.ERROR,"Thanks a comment[id=" + commentId + "] failed",e);
    throw new ServiceException(e);
  }
}
