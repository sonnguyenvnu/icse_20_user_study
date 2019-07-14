/** 
 * Gets a comment's revisions.
 * @param context the specified context
 */
@RequestProcessing(value="/comment/{id}/revisions",method=HttpMethod.GET) @Before({StopwatchStartAdvice.class,LoginCheck.class,PermissionCheck.class}) @After({StopwatchEndAdvice.class}) public void getCommentRevisions(final RequestContext context){
  final String id=context.pathVar("id");
  final JSONObject viewer=Sessions.getUser();
  final List<JSONObject> revisions=revisionQueryService.getCommentRevisions(viewer,id);
  final JSONObject ret=new JSONObject();
  ret.put(Keys.STATUS_CODE,true);
  ret.put(Revision.REVISIONS,(Object)revisions);
  context.renderJSON(ret);
}
