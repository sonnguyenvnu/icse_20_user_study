/** 
 * Makes article/comment read.
 * @param context the specified context
 */
@RequestProcessing(value="/notifications/read",method=HttpMethod.POST) @Before({StopwatchStartAdvice.class,LoginCheck.class}) @After(StopwatchEndAdvice.class) public void makeNotificationRead(final RequestContext context){
  final JSONObject requestJSONObject=context.requestJSON();
  final JSONObject currentUser=Sessions.getUser();
  final String userId=currentUser.optString(Keys.OBJECT_ID);
  final String articleId=requestJSONObject.optString(Article.ARTICLE_T_ID);
  final List<String> commentIds=Arrays.asList(requestJSONObject.optString(Comment.COMMENT_T_IDS).split(","));
  notificationMgmtService.makeRead(userId,articleId,commentIds);
  context.renderJSON(true);
}
