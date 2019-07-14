/** 
 * Queries tags.
 * @param context the specified context
 */
@RequestProcessing(value="/tags/query",method=HttpMethod.GET) public void queryTags(final RequestContext context){
  if (!Sessions.isLoggedIn()) {
    context.setStatus(HttpServletResponse.SC_FORBIDDEN);
    return;
  }
  context.renderJSON().renderTrueResult();
  final String titlePrefix=context.param("title");
  List<JSONObject> tags;
  final int fetchSize=7;
  if (StringUtils.isBlank(titlePrefix)) {
    tags=tagQueryService.getTags(fetchSize);
  }
 else {
    tags=tagQueryService.getTagsByPrefix(titlePrefix,fetchSize);
  }
  final List<String> ret=new ArrayList<>();
  for (  final JSONObject tag : tags) {
    ret.add(tag.optString(Tag.TAG_TITLE));
  }
  context.renderJSONValue(Tag.TAGS,ret);
}
