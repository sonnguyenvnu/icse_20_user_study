/** 
 * Follows an article. <p> The request json object: <pre> { "followingId": "" } </pre> </p>
 * @param context the specified context
 */
@RequestProcessing(value="/follow/article",method=HttpMethod.POST) @Before({LoginCheck.class,PermissionCheck.class}) public void followArticle(final RequestContext context){
  context.renderJSON();
  final HttpServletRequest request=context.getRequest();
  final JSONObject requestJSONObject=context.requestJSON();
  final String followingArticleId=requestJSONObject.optString(Follow.FOLLOWING_ID);
  final JSONObject currentUser=Sessions.getUser();
  final String followerUserId=currentUser.optString(Keys.OBJECT_ID);
  followMgmtService.followArticle(followerUserId,followingArticleId);
  final JSONObject article=articleQueryService.getArticle(followingArticleId);
  final String articleAuthorId=article.optString(Article.ARTICLE_AUTHOR_ID);
  if (!FOLLOWS.contains(articleAuthorId + followingArticleId + "-" + followerUserId) && !articleAuthorId.equals(followerUserId)) {
    final JSONObject notification=new JSONObject();
    notification.put(Notification.NOTIFICATION_USER_ID,articleAuthorId);
    notification.put(Notification.NOTIFICATION_DATA_ID,followingArticleId + "-" + followerUserId);
    notificationMgmtService.addArticleNewFollowerNotification(notification);
  }
  FOLLOWS.add(articleAuthorId + followingArticleId + "-" + followerUserId);
  context.renderTrueResult();
}
