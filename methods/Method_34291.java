private Set<String> getScope(HttpServletRequest request){
  return OAuth2Utils.parseParameterList(request.getParameter("scope"));
}
