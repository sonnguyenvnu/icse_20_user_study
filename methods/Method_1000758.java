public View match(ActionContext ac){
  HttpServletResponse resp=ac.getResponse();
  if (!Strings.isBlank(origin))   resp.setHeader("Access-Control-Allow-Origin",origin);
  if (!Strings.isBlank(methods))   resp.setHeader("Access-Control-Allow-Methods",methods);
  if (!Strings.isBlank(headers))   resp.setHeader("Access-Control-Allow-Headers",headers);
  if (!Strings.isBlank(credentials))   resp.setHeader("Access-Control-Allow-Credentials",credentials);
  if ("OPTIONS".equals(ac.getRequest().getMethod())) {
    if (log.isDebugEnabled())     log.debugf("Feedback -- [%s] [%s] [%s] [%s]",origin,methods,headers,credentials);
    return new VoidView();
  }
  return null;
}
