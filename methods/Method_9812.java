/** 
 * Accepts a comment.
 * @param context the specified context
 */
@RequestProcessing(value="/comment/accept",method=HttpMethod.POST) @Before({LoginCheck.class,CSRFCheck.class,PermissionCheck.class}) public void acceptComment(final RequestContext context){
  context.renderJSON();
  final HttpServletRequest request=context.getRequest();
  final JSONObject requestJSONObject=context.requestJSON();
  final JSONObject currentUser=Sessions.getUser();
  final String userId=currentUser.optString(Keys.OBJECT_ID);
  final String commentId=requestJSONObject.optString(Comment.COMMENT_T_ID);
  try {
    final JSONObject comment=commentQueryService.getComment(commentId);
    if (null == comment) {
      context.renderFalseResult().renderMsg("Not found comment to accept");
      return;
    }
    final String commentAuthorId=comment.optString(Comment.COMMENT_AUTHOR_ID);
    if (StringUtils.equals(userId,commentAuthorId)) {
      context.renderFalseResult().renderMsg(langPropsService.get("thankSelfLabel"));
      return;
    }
    final String articleId=comment.optString(Comment.COMMENT_ON_ARTICLE_ID);
    final JSONObject article=articleQueryService.getArticle(articleId);
    if (!StringUtils.equals(userId,article.optString(Article.ARTICLE_AUTHOR_ID))) {
      context.renderFalseResult().renderMsg(langPropsService.get("sc403Label"));
      return;
    }
    commentMgmtService.acceptComment(commentId);
    context.renderTrueResult();
  }
 catch (  final ServiceException e) {
    context.renderMsg(e.getMessage());
  }
}
