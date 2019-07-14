/** 
 * Updates a comment.
 * @param context the specified context
 */
@RequestProcessing(value="/admin/comment/{commentId}",method=HttpMethod.POST) @Before({StopwatchStartAdvice.class,PermissionCheck.class}) @After({PermissionGrant.class,StopwatchEndAdvice.class}) public void updateComment(final RequestContext context){
  final String commentId=context.pathVar("commentId");
  final HttpServletRequest request=context.getRequest();
  final HttpServletResponse response=context.getResponse();
  final AbstractFreeMarkerRenderer renderer=new SkinRenderer(context,"admin/comment.ftl");
  final Map<String,Object> dataModel=renderer.getDataModel();
  JSONObject comment=commentQueryService.getComment(commentId);
  final Enumeration<String> parameterNames=request.getParameterNames();
  while (parameterNames.hasMoreElements()) {
    final String name=parameterNames.nextElement();
    final String value=context.param(name);
    if (name.equals(Comment.COMMENT_STATUS) || name.equals(Comment.COMMENT_THANK_CNT) || name.equals(Comment.COMMENT_GOOD_CNT) || name.equals(Comment.COMMENT_BAD_CNT)) {
      comment.put(name,Integer.valueOf(value));
    }
 else {
      comment.put(name,value);
    }
  }
  commentMgmtService.updateCommentByAdmin(commentId,comment);
  operationMgmtService.addOperation(Operation.newOperation(request,Operation.OPERATION_CODE_C_UPDATE_COMMENT,commentId));
  comment=commentQueryService.getComment(commentId);
  dataModel.put(Comment.COMMENT,comment);
  dataModelService.fillHeaderAndFooter(context,dataModel);
}
