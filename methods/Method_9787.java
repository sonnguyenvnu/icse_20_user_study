/** 
 * Sticks an article.
 * @param context the specified HTTP request context
 */
@RequestProcessing(value="/article/stick",method=HttpMethod.POST) @Before({StopwatchStartAdvice.class,PermissionCheck.class}) @After(StopwatchEndAdvice.class) public void stickArticle(final RequestContext context){
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
  final JSONObject article=articleQueryService.getArticle(articleId);
  if (null == article) {
    context.sendError(HttpServletResponse.SC_NOT_FOUND);
    return;
  }
  if (!currentUser.optString(Keys.OBJECT_ID).equals(article.optString(Article.ARTICLE_AUTHOR_ID))) {
    context.sendError(HttpServletResponse.SC_FORBIDDEN);
    return;
  }
  context.renderJSON();
  try {
    articleMgmtService.stick(articleId);
  }
 catch (  final ServiceException e) {
    context.renderMsg(e.getMessage());
    return;
  }
  context.renderTrueResult().renderMsg(langPropsService.get("stickSuccLabel"));
}
