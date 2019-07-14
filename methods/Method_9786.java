/** 
 * Article thanks.
 * @param context the specified http request context
 */
@RequestProcessing(value="/article/thank",method=HttpMethod.POST) @Before({StopwatchStartAdvice.class,PermissionCheck.class}) @After(StopwatchEndAdvice.class) public void thank(final RequestContext context){
  final HttpServletRequest request=context.getRequest();
  final HttpServletResponse response=context.getResponse();
  final JSONObject currentUser=Sessions.getUser();
  if (null == currentUser) {
    context.sendError(HttpServletResponse.SC_FORBIDDEN);
    return;
  }
  final String articleId=context.param(Article.ARTICLE_T_ID);
  if (StringUtils.isBlank(articleId)) {
    context.sendError(HttpServletResponse.SC_BAD_REQUEST);
    return;
  }
  context.renderJSON();
  try {
    articleMgmtService.thank(articleId,currentUser.optString(Keys.OBJECT_ID));
  }
 catch (  final ServiceException e) {
    context.renderMsg(langPropsService.get("transferFailLabel"));
    return;
  }
  context.renderTrueResult();
}
