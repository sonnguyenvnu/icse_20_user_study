@Override public void doAdvice(final RequestContext context) throws RequestProcessAdviceException {
  final HttpServletRequest request=context.getRequest();
  final JSONObject requestJSONObject=context.requestJSON();
  request.setAttribute(Keys.REQUEST,requestJSONObject);
  validateCommentFields(requestJSONObject);
}
