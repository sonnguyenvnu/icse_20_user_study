@Override public void action(final Event<JSONObject> event){
  final JSONObject data=event.getData();
  LOGGER.log(Level.TRACE,"Processing an event [type={0}, data={1}]",event.getType(),data);
  try {
    final JSONObject originalArticle=data.getJSONObject(Article.ARTICLE);
    final String articleId=originalArticle.optString(Keys.OBJECT_ID);
    final String articleAuthorId=originalArticle.optString(Article.ARTICLE_AUTHOR_ID);
    final JSONObject articleAuthor=userQueryService.getUser(articleAuthorId);
    final String articleAuthorName=articleAuthor.optString(User.USER_NAME);
    final Set<String> requisiteAtUserPermissions=new HashSet<>();
    requisiteAtUserPermissions.add(Permission.PERMISSION_ID_C_COMMON_AT_USER);
    final boolean hasAtUserPerm=roleQueryService.userHasPermissions(articleAuthorId,requisiteAtUserPermissions);
    final Set<String> atedUserIds=new HashSet<>();
    if (hasAtUserPerm) {
      final String articleContent=originalArticle.optString(Article.ARTICLE_CONTENT);
      final Set<String> atUserNames=userQueryService.getUserNames(articleContent);
      atUserNames.remove(articleAuthorName);
      for (      final String userName : atUserNames) {
        final JSONObject user=userQueryService.getUserByName(userName);
        final JSONObject requestJSONObject=new JSONObject();
        final String atedUserId=user.optString(Keys.OBJECT_ID);
        requestJSONObject.put(Notification.NOTIFICATION_USER_ID,atedUserId);
        requestJSONObject.put(Notification.NOTIFICATION_DATA_ID,articleId);
        notificationMgmtService.addAtNotification(requestJSONObject);
        atedUserIds.add(atedUserId);
      }
    }
    final String tags=originalArticle.optString(Article.ARTICLE_TAGS);
    final boolean articleNotifyFollowers=data.optBoolean(Article.ARTICLE_T_NOTIFY_FOLLOWERS);
    if (articleNotifyFollowers && Article.ARTICLE_TYPE_C_DISCUSSION != originalArticle.optInt(Article.ARTICLE_TYPE) && Article.ARTICLE_ANONYMOUS_C_PUBLIC == originalArticle.optInt(Article.ARTICLE_ANONYMOUS) && !Tag.TAG_TITLE_C_SANDBOX.equals(tags) && !StringUtils.containsIgnoreCase(tags,Symphonys.SYS_ANNOUNCE_TAG)) {
      final JSONObject followerUsersResult=followQueryService.getFollowerUsers(articleAuthorId,1,Integer.MAX_VALUE);
      final List<JSONObject> followerUsers=(List<JSONObject>)followerUsersResult.opt(Keys.RESULTS);
      final long thirtyDaysAgo=DateUtils.addDays(new Date(),-30).getTime();
      for (      final JSONObject followerUser : followerUsers) {
        final long latestLoginTime=followerUser.optLong(UserExt.USER_LATEST_LOGIN_TIME);
        if (latestLoginTime < thirtyDaysAgo) {
          continue;
        }
        final JSONObject requestJSONObject=new JSONObject();
        final String followerUserId=followerUser.optString(Keys.OBJECT_ID);
        if (atedUserIds.contains(followerUserId)) {
          continue;
        }
        requestJSONObject.put(Notification.NOTIFICATION_USER_ID,followerUserId);
        requestJSONObject.put(Notification.NOTIFICATION_DATA_ID,articleId);
        notificationMgmtService.addFollowingUserNotification(requestJSONObject);
      }
    }
    final String articleTitle=Escapes.escapeHTML(originalArticle.optString(Article.ARTICLE_TITLE));
    if (Article.ARTICLE_TYPE_C_CITY_BROADCAST == originalArticle.optInt(Article.ARTICLE_TYPE) && Article.ARTICLE_ANONYMOUS_C_PUBLIC == originalArticle.optInt(Article.ARTICLE_ANONYMOUS)) {
      final String city=originalArticle.optString(Article.ARTICLE_CITY);
      if (StringUtils.isNotBlank(city)) {
        final JSONObject requestJSONObject=new JSONObject();
        requestJSONObject.put(Pagination.PAGINATION_CURRENT_PAGE_NUM,1);
        requestJSONObject.put(Pagination.PAGINATION_PAGE_SIZE,Integer.MAX_VALUE);
        requestJSONObject.put(Pagination.PAGINATION_WINDOW_SIZE,Integer.MAX_VALUE);
        final long latestLoginTime=DateUtils.addDays(new Date(),-15).getTime();
        requestJSONObject.put(UserExt.USER_LATEST_LOGIN_TIME,latestLoginTime);
        requestJSONObject.put(UserExt.USER_CITY,city);
        final JSONObject result=userQueryService.getUsersByCity(requestJSONObject);
        final JSONArray users=result.optJSONArray(User.USERS);
        for (int i=0; i < users.length(); i++) {
          final String userId=users.optJSONObject(i).optString(Keys.OBJECT_ID);
          if (userId.equals(articleAuthorId)) {
            continue;
          }
          final JSONObject notification=new JSONObject();
          notification.put(Notification.NOTIFICATION_USER_ID,userId);
          notification.put(Notification.NOTIFICATION_DATA_ID,articleId);
          notificationMgmtService.addBroadcastNotification(notification);
        }
        LOGGER.info("City [" + city + "] broadcast [users=" + users.length() + "]");
      }
    }
    if (StringUtils.containsIgnoreCase(tags,Symphonys.SYS_ANNOUNCE_TAG)) {
      final long latestLoginTime=DateUtils.addDays(new Date(),-15).getTime();
      final JSONObject result=userQueryService.getLatestLoggedInUsers(latestLoginTime,1,Integer.MAX_VALUE,Integer.MAX_VALUE);
      final JSONArray users=result.optJSONArray(User.USERS);
      for (int i=0; i < users.length(); i++) {
        final String userId=users.optJSONObject(i).optString(Keys.OBJECT_ID);
        final JSONObject notification=new JSONObject();
        notification.put(Notification.NOTIFICATION_USER_ID,userId);
        notification.put(Notification.NOTIFICATION_DATA_ID,articleId);
        notificationMgmtService.addSysAnnounceArticleNotification(notification);
      }
      LOGGER.info("System announcement [" + articleTitle + "] broadcast [users=" + users.length() + "]");
    }
  }
 catch (  final Exception e) {
    LOGGER.log(Level.ERROR,"Sends the article add notification failed",e);
  }
}
