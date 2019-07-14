@Override public void doAdvice(final RequestContext context) throws RequestProcessAdviceException {
  final HttpServletRequest request=context.getRequest();
  final JSONObject exception=new JSONObject();
  exception.put(Keys.MSG,langPropsService.get("csrfCheckFailedLabel"));
  exception.put(Keys.STATUS_CODE,false);
  final String referer=context.header("Referer");
  if (!StringUtils.startsWith(referer,StringUtils.substringBeforeLast(Latkes.getServePath(),":"))) {
    throw new RequestProcessAdviceException(exception);
  }
  final String clientToken=context.header(Common.CSRF_TOKEN);
  final String serverToken=Sessions.getCSRFToken(context);
  if (!StringUtils.equals(clientToken,serverToken)) {
    throw new RequestProcessAdviceException(exception);
  }
}
