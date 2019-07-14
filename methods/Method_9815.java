/** 
 * Gets a comment's content.
 * @param context the specified context
 */
@RequestProcessing(value="/comment/{id}/content",method=HttpMethod.GET) @Before({LoginCheck.class}) public void getCommentContent(final RequestContext context){
  final String id=context.pathVar("id");
  context.renderJSON().renderJSONValue(Keys.STATUS_CODE,StatusCodes.ERR);
  final JSONObject comment=commentQueryService.getComment(id);
  if (null == comment) {
    LOGGER.warn("Not found comment [id=" + id + "] to update");
    return;
  }
  final HttpServletRequest request=context.getRequest();
  final HttpServletResponse response=context.getResponse();
  final JSONObject currentUser=Sessions.getUser();
  if (!currentUser.optString(Keys.OBJECT_ID).equals(comment.optString(Comment.COMMENT_AUTHOR_ID))) {
    context.sendError(HttpServletResponse.SC_FORBIDDEN);
    return;
  }
  context.renderJSONValue(Comment.COMMENT_CONTENT,comment.optString(Comment.COMMENT_CONTENT));
  context.renderJSONValue(Comment.COMMENT_VISIBLE,comment.optInt(Comment.COMMENT_VISIBLE));
  context.renderJSONValue(Keys.STATUS_CODE,StatusCodes.SUCC);
}
