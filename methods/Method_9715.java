@Override public void action(final Event<JSONObject> event){
  final JSONObject data=event.getData();
  LOGGER.log(Level.TRACE,"Processing an event [type={0}, data={1}]",event.getType(),data);
  try {
    final JSONObject articleUpdated=data.getJSONObject(Article.ARTICLE);
    final String articleId=articleUpdated.optString(Keys.OBJECT_ID);
    final String articleAuthorId=articleUpdated.optString(Article.ARTICLE_AUTHOR_ID);
    final JSONObject articleAuthor=userQueryService.getUser(articleAuthorId);
    final String articleAuthorName=articleAuthor.optString(User.USER_NAME);
    final boolean isDiscussion=articleUpdated.optInt(Article.ARTICLE_TYPE) == Article.ARTICLE_TYPE_C_DISCUSSION;
    final String articleContent=articleUpdated.optString(Article.ARTICLE_CONTENT);
    final Set<String> atUserNames=userQueryService.getUserNames(articleContent);
    atUserNames.remove(articleAuthorName);
    final Set<String> requisiteAtUserPermissions=new HashSet<>();
    requisiteAtUserPermissions.add(Permission.PERMISSION_ID_C_COMMON_AT_USER);
    final boolean hasAtUserPerm=roleQueryService.userHasPermissions(articleAuthorId,requisiteAtUserPermissions);
    final Set<String> atedUserIds=new HashSet<>();
    if (hasAtUserPerm) {
      for (      final String userName : atUserNames) {
        final JSONObject user=userQueryService.getUserByName(userName);
        final JSONObject requestJSONObject=new JSONObject();
        final String atedUserId=user.optString(Keys.OBJECT_ID);
        if (!notificationRepository.hasSentByDataIdAndType(atedUserId,articleId,Notification.DATA_TYPE_C_AT)) {
          requestJSONObject.put(Notification.NOTIFICATION_USER_ID,atedUserId);
          requestJSONObject.put(Notification.NOTIFICATION_DATA_ID,articleId);
          notificationMgmtService.addAtNotification(requestJSONObject);
        }
        atedUserIds.add(atedUserId);
      }
    }
    final JSONObject oldArticle=data.optJSONObject(Common.OLD_ARTICLE);
    if (!Article.isDifferent(oldArticle,articleUpdated)) {
      LOGGER.log(Level.DEBUG,"The article [title=" + oldArticle.optString(Article.ARTICLE_TITLE) + "] has not changed, do not notify it's watchers");
      return;
    }
    final boolean articleNotifyFollowers=data.optBoolean(Article.ARTICLE_T_NOTIFY_FOLLOWERS);
    if (articleNotifyFollowers) {
      final JSONObject followerUsersResult=followQueryService.getArticleWatchers(UserExt.USER_AVATAR_VIEW_MODE_C_ORIGINAL,articleId,1,Integer.MAX_VALUE);
      final List<JSONObject> watcherUsers=(List<JSONObject>)followerUsersResult.opt(Keys.RESULTS);
      for (      final JSONObject watcherUser : watcherUsers) {
        final String watcherName=watcherUser.optString(User.USER_NAME);
        if ((isDiscussion && !atUserNames.contains(watcherName)) || articleAuthorName.equals(watcherName)) {
          continue;
        }
        final JSONObject requestJSONObject=new JSONObject();
        final String watcherUserId=watcherUser.optString(Keys.OBJECT_ID);
        requestJSONObject.put(Notification.NOTIFICATION_USER_ID,watcherUserId);
        requestJSONObject.put(Notification.NOTIFICATION_DATA_ID,articleId);
        notificationMgmtService.addFollowingArticleUpdateNotification(requestJSONObject);
      }
    }
  }
 catch (  final Exception e) {
    LOGGER.log(Level.ERROR,"Sends the article update notification failed",e);
  }
}
