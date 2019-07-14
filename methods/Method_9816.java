/** 
 * Thanks a comment.
 * @param context the specified context
 */
@RequestProcessing(value="/comment/thank",method=HttpMethod.POST) @Before({LoginCheck.class,CSRFCheck.class,PermissionCheck.class}) public void thankComment(final RequestContext context){
  context.renderJSON();
  final HttpServletRequest request=context.getRequest();
  final JSONObject requestJSONObject=context.requestJSON();
  final JSONObject currentUser=Sessions.getUser();
  final String commentId=requestJSONObject.optString(Comment.COMMENT_T_ID);
  try {
    commentMgmtService.thankComment(commentId,currentUser.optString(Keys.OBJECT_ID));
    context.renderTrueResult().renderMsg(langPropsService.get("thankSentLabel"));
  }
 catch (  final ServiceException e) {
    context.renderMsg(e.getMessage());
  }
}
