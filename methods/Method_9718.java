@Override public void action(final Event<JSONObject> event){
  final JSONObject data=event.getData();
  LOGGER.log(Level.TRACE,"Processing an event [type={0}, data={1}]",event.getType(),data);
  try {
    final JSONObject originalArticle=data.getJSONObject(Article.ARTICLE);
    final JSONObject originalComment=data.getJSONObject(Comment.COMMENT);
    final String commentId=originalComment.optString(Keys.OBJECT_ID);
    final String commenterId=originalComment.optString(Comment.COMMENT_AUTHOR_ID);
    final String commentContent=originalComment.optString(Comment.COMMENT_CONTENT);
    final JSONObject commenter=userQueryService.getUser(commenterId);
    final String commenterName=commenter.optString(User.USER_NAME);
    final Set<String> atUserNames=userQueryService.getUserNames(commentContent);
    atUserNames.remove(commenterName);
    final boolean isDiscussion=originalArticle.optInt(Article.ARTICLE_TYPE) == Article.ARTICLE_TYPE_C_DISCUSSION;
    final String articleAuthorId=originalArticle.optString(Article.ARTICLE_AUTHOR_ID);
    final String articleContent=originalArticle.optString(Article.ARTICLE_CONTENT);
    final Set<String> articleContentAtUserNames=userQueryService.getUserNames(articleContent);
    final Set<String> requisiteAtUserPermissions=new HashSet<>();
    requisiteAtUserPermissions.add(Permission.PERMISSION_ID_C_COMMON_AT_USER);
    final boolean hasAtUserPerm=roleQueryService.userHasPermissions(commenterId,requisiteAtUserPermissions);
    final Set<String> atIds=new HashSet<>();
    if (hasAtUserPerm) {
      for (      final String userName : atUserNames) {
        if (isDiscussion && !articleContentAtUserNames.contains(userName)) {
          continue;
        }
        final JSONObject atUser=userQueryService.getUserByName(userName);
        if (atUser.optString(Keys.OBJECT_ID).equals(articleAuthorId)) {
          continue;
        }
        final String atUserId=atUser.optString(Keys.OBJECT_ID);
        if (!notificationRepository.hasSentByDataIdAndType(atUserId,commentId,Notification.DATA_TYPE_C_AT)) {
          final JSONObject requestJSONObject=new JSONObject();
          requestJSONObject.put(Notification.NOTIFICATION_USER_ID,atUserId);
          requestJSONObject.put(Notification.NOTIFICATION_DATA_ID,commentId);
          notificationMgmtService.addAtNotification(requestJSONObject);
        }
        atIds.add(atUserId);
      }
    }
  }
 catch (  final Exception e) {
    LOGGER.log(Level.ERROR,"Sends the comment update notification failed",e);
  }
}
