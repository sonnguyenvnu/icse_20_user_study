@Override @SuppressFBWarnings({"SERVLET_HEADER_REFERER","SERVLET_HEADER_USER_AGENT"}) public String format(ContainerRequestType servletRequest,ContainerResponseType servletResponse,SecurityContext ctx){
  StringBuilder logLineBuilder=new StringBuilder();
  AwsProxyRequest req=(AwsProxyRequest)servletRequest.getAttribute(API_GATEWAY_EVENT_PROPERTY);
  AwsProxyRequestContext gatewayContext=req.getRequestContext();
  logLineBuilder.append(servletRequest.getRemoteAddr());
  logLineBuilder.append(" ");
  if (gatewayContext != null && req.getRequestSource() == AwsProxyRequest.RequestSource.API_GATEWAY) {
    if (gatewayContext.getIdentity().getUserArn() != null) {
      logLineBuilder.append(gatewayContext.getIdentity().getUserArn());
    }
 else {
      logLineBuilder.append("-");
    }
  }
 else {
    logLineBuilder.append("-");
  }
  logLineBuilder.append(" ");
  if (ctx != null && ctx.getUserPrincipal().getName() != null) {
    logLineBuilder.append(ctx.getUserPrincipal().getName());
    logLineBuilder.append(" ");
  }
  if (gatewayContext != null) {
    logLineBuilder.append(dateFormat.format(ZonedDateTime.of(LocalDateTime.ofEpochSecond(gatewayContext.getRequestTimeEpoch() / 1000,0,ZoneOffset.UTC),ZoneId.systemDefault())));
  }
 else {
    logLineBuilder.append(dateFormat.format(ZonedDateTime.now()));
  }
  logLineBuilder.append(" ");
  logLineBuilder.append("\"");
  logLineBuilder.append(servletRequest.getMethod().toUpperCase(Locale.ENGLISH));
  logLineBuilder.append(" ");
  logLineBuilder.append(servletRequest.getRequestURI());
  logLineBuilder.append(" ");
  logLineBuilder.append(servletRequest.getProtocol());
  logLineBuilder.append("\" ");
  logLineBuilder.append(servletResponse.getStatus());
  logLineBuilder.append(" ");
  if (servletResponse instanceof AwsHttpServletResponse) {
    AwsHttpServletResponse awsResponse=(AwsHttpServletResponse)servletResponse;
    if (awsResponse.getAwsResponseBodyBytes().length > 0) {
      logLineBuilder.append(awsResponse.getAwsResponseBodyBytes().length);
    }
 else {
      logLineBuilder.append("-");
    }
  }
 else {
    logLineBuilder.append("-");
  }
  logLineBuilder.append(" ");
  logLineBuilder.append("\"");
  if (servletRequest.getHeader("referer") != null) {
    logLineBuilder.append(servletRequest.getHeader("referer"));
  }
 else {
    logLineBuilder.append("-");
  }
  logLineBuilder.append("\" ");
  logLineBuilder.append("\"");
  if (servletRequest.getHeader("user-agent") != null) {
    logLineBuilder.append(servletRequest.getHeader("user-agent"));
  }
 else {
    logLineBuilder.append("-");
  }
  logLineBuilder.append("\" ");
  logLineBuilder.append("combined");
  return logLineBuilder.toString();
}
