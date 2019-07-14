/** 
 * Unfollows an article. <p> The request json object: <pre> { "followingId": "" } </pre> </p>
 * @param context the specified context
 */
@RequestProcessing(value="/unfollow/article",method=HttpMethod.POST) @Before(LoginCheck.class) public void unfollowArticle(final RequestContext context){
  context.renderJSON();
  final HttpServletRequest request=context.getRequest();
  final JSONObject requestJSONObject=context.requestJSON();
  final String followingArticleId=requestJSONObject.optString(Follow.FOLLOWING_ID);
  final JSONObject currentUser=Sessions.getUser();
  final String followerUserId=currentUser.optString(Keys.OBJECT_ID);
  followMgmtService.unfollowArticle(followerUserId,followingArticleId);
  context.renderTrueResult();
}
