/** 
 * Shows a comment.
 * @param context the specified context
 */
@RequestProcessing(value="/admin/comment/{commentId}",method=HttpMethod.GET) @Before({StopwatchStartAdvice.class,PermissionCheck.class}) @After({PermissionGrant.class,StopwatchEndAdvice.class}) public void showComment(final RequestContext context){
  final String commentId=context.pathVar("commentId");
  final AbstractFreeMarkerRenderer renderer=new SkinRenderer(context,"admin/comment.ftl");
  final Map<String,Object> dataModel=renderer.getDataModel();
  final JSONObject comment=commentQueryService.getComment(commentId);
  Escapes.escapeHTML(comment);
  dataModel.put(Comment.COMMENT,comment);
  dataModelService.fillHeaderAndFooter(context,dataModel);
}
