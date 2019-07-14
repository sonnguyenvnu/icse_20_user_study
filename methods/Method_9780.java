/** 
 * Gets an article's revisions.
 * @param context the specified context
 */
@RequestProcessing(value="/article/{id}/revisions",method=HttpMethod.GET) @Before({StopwatchStartAdvice.class,LoginCheck.class,PermissionCheck.class}) @After({StopwatchEndAdvice.class}) public void getArticleRevisions(final RequestContext context){
  final String id=context.pathVar("id");
  final List<JSONObject> revisions=revisionQueryService.getArticleRevisions(id);
  final JSONObject ret=new JSONObject();
  ret.put(Keys.STATUS_CODE,true);
  ret.put(Revision.REVISIONS,(Object)revisions);
  context.renderJSON(ret);
}
