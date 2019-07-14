/** 
 * Gets article preview content. <p> Renders the response with a json object, for example, <pre> { "html": "" } </pre> </p>
 * @param context the specified http request context
 */
@RequestProcessing(value="/article/{articleId}/preview",method=HttpMethod.GET) @Before(StopwatchStartAdvice.class) @After(StopwatchEndAdvice.class) public void getArticlePreviewContent(final RequestContext context){
  final String articleId=context.pathVar("articleId");
  final HttpServletRequest request=context.getRequest();
  final String content=articleQueryService.getArticlePreviewContent(articleId,context);
  if (StringUtils.isBlank(content)) {
    context.renderJSON().renderFalseResult();
    return;
  }
  context.renderJSON(true).renderJSONValue("html",content);
}
