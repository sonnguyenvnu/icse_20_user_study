public View match(ActionContext context){
  HttpSession session=Mvcs.getHttpSession(false);
  if (session == null || null == session.getAttribute(name))   return new ServerRedirectView(path);
  return null;
}
