/** 
 * Votes up a comment. <p> The request json object: <pre> { "dataId": "" } </pre> </p>
 * @param context the specified context
 */
@RequestProcessing(value="/vote/up/comment",method=HttpMethod.POST) @Before({LoginCheck.class,PermissionCheck.class}) public void voteUpComment(final RequestContext context){
  context.renderJSON();
  final HttpServletRequest request=context.getRequest();
  final JSONObject requestJSONObject=context.requestJSON();
  final String dataId=requestJSONObject.optString(Common.DATA_ID);
  final JSONObject currentUser=Sessions.getUser();
  final String userId=currentUser.optString(Keys.OBJECT_ID);
  if (!Role.ROLE_ID_C_ADMIN.equals(currentUser.optString(User.USER_ROLE)) && voteQueryService.isOwn(userId,dataId,Vote.DATA_TYPE_C_COMMENT)) {
    context.renderFalseResult().renderMsg(langPropsService.get("cantVoteSelfLabel"));
    return;
  }
  final int vote=voteQueryService.isVoted(userId,dataId);
  if (Vote.TYPE_C_UP == vote) {
    voteMgmtService.voteCancel(userId,dataId,Vote.DATA_TYPE_C_COMMENT);
  }
 else {
    voteMgmtService.voteUp(userId,dataId,Vote.DATA_TYPE_C_COMMENT);
    final JSONObject comment=commentQueryService.getComment(dataId);
    final String commenterId=comment.optString(Comment.COMMENT_AUTHOR_ID);
    if (!VOTES.contains(userId + dataId) && !userId.equals(commenterId)) {
      final JSONObject notification=new JSONObject();
      notification.put(Notification.NOTIFICATION_USER_ID,commenterId);
      notification.put(Notification.NOTIFICATION_DATA_ID,dataId + "-" + userId);
      notificationMgmtService.addCommentVoteUpNotification(notification);
    }
    VOTES.add(userId + dataId);
  }
  context.renderTrueResult().renderJSONValue(Vote.TYPE,vote);
}
