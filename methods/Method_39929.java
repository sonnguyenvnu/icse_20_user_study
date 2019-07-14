@Override public void doTag() throws JspException {
  PageContext pageContext=(PageContext)getJspContext();
  HttpServletRequest request=(HttpServletRequest)pageContext.getRequest();
  String scopeValue=scope != null ? scope.toLowerCase() : SCOPE_PAGE;
  if (scopeValue.equals(SCOPE_APPLICATION)) {
    request.getServletContext().removeAttribute(name);
  }
 else   if (scopeValue.equals(SCOPE_SESSION)) {
    request.getSession().removeAttribute(name);
  }
 else   if (scopeValue.equals(SCOPE_REQUEST)) {
    request.removeAttribute(name);
  }
 else   if (scopeValue.equals(SCOPE_PAGE)) {
    pageContext.removeAttribute(name);
  }
 else {
    throw new JspException("Invalid scope: " + scope);
  }
}
