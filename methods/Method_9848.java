/** 
 * Exports posts(article/comment) to a file.
 * @param context the specified context
 */
@RequestProcessing(value="/export/posts",method=HttpMethod.POST) @Before({LoginCheck.class}) public void exportPosts(final RequestContext context){
  context.renderJSON();
  final JSONObject user=Sessions.getUser();
  final String userId=user.optString(Keys.OBJECT_ID);
  final String downloadURL=postExportService.exportPosts(userId);
  if ("-1".equals(downloadURL)) {
    context.renderJSONValue(Keys.MSG,langPropsService.get("insufficientBalanceLabel"));
  }
 else   if (StringUtils.isBlank(downloadURL)) {
    return;
  }
  context.renderJSON(true).renderJSONValue("url",downloadURL);
}
