@Override public void doTag() throws JspException {
  final PageContext pageContext=((PageContext)getJspContext());
  final HttpServletRequest request=(HttpServletRequest)pageContext.getRequest();
  final UserSession userSession=UserSession.get(request);
  final boolean invokeBody=(userSession != null) == auth;
  if (invokeBody) {
    TagUtil.invokeBody(getJspBody());
  }
}
