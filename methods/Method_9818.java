/** 
 * Follows a user. <p> The request json object: <pre> { "followingId": "" } </pre> </p>
 * @param context the specified context
 */
@RequestProcessing(value="/follow/user",method=HttpMethod.POST) @Before(LoginCheck.class) public void followUser(final RequestContext context){
  context.renderJSON();
  final HttpServletRequest request=context.getRequest();
  final JSONObject requestJSONObject=context.requestJSON();
  final String followingUserId=requestJSONObject.optString(Follow.FOLLOWING_ID);
  final JSONObject currentUser=Sessions.getUser();
  final String followerUserId=currentUser.optString(Keys.OBJECT_ID);
  followMgmtService.followUser(followerUserId,followingUserId);
  if (!FOLLOWS.contains(followingUserId + followerUserId)) {
    final JSONObject notification=new JSONObject();
    notification.put(Notification.NOTIFICATION_USER_ID,followingUserId);
    notification.put(Notification.NOTIFICATION_DATA_ID,followerUserId);
    notificationMgmtService.addNewFollowerNotification(notification);
  }
  FOLLOWS.add(followingUserId + followerUserId);
  context.renderTrueResult();
}
