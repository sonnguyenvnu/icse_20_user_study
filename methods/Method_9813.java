/** 
 * Removes a comment.
 * @param context the specified context
 */
@RequestProcessing(value="/comment/{id}/remove",method=HttpMethod.POST) @Before({StopwatchStartAdvice.class,LoginCheck.class,PermissionCheck.class}) @After({StopwatchEndAdvice.class}) public void removeComment(final RequestContext context){
  final String id=context.pathVar("id");
  final HttpServletRequest request=context.getRequest();
  if (StringUtils.isBlank(id)) {
    context.sendError(HttpServletResponse.SC_NOT_FOUND);
    return;
  }
  final JSONObject currentUser=Sessions.getUser();
  final String currentUserId=currentUser.optString(Keys.OBJECT_ID);
  final JSONObject comment=commentQueryService.getComment(id);
  if (null == comment) {
    context.sendError(HttpServletResponse.SC_NOT_FOUND);
    return;
  }
  final String authorId=comment.optString(Comment.COMMENT_AUTHOR_ID);
  if (!authorId.equals(currentUserId)) {
    context.sendError(HttpServletResponse.SC_FORBIDDEN);
    return;
  }
  context.renderJSON();
  try {
    commentMgmtService.removeComment(id);
    context.renderJSONValue(Keys.STATUS_CODE,StatusCodes.SUCC);
    context.renderJSONValue(Comment.COMMENT_T_ID,id);
  }
 catch (  final ServiceException e) {
    final String msg=e.getMessage();
    context.renderMsg(msg);
    context.renderJSONValue(Keys.STATUS_CODE,StatusCodes.ERR);
  }
}
