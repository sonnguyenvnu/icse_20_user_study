protected String createTemplate(Map<String,Object> model,HttpServletRequest request){
  AuthorizationRequest authorizationRequest=(AuthorizationRequest)model.get("authorizationRequest");
  String clientId=authorizationRequest.getClientId();
  StringBuilder builder=new StringBuilder();
  builder.append("<html><body><h1>OAuth Approval</h1>");
  builder.append("<p>Do you authorize \"").append(HtmlUtils.htmlEscape(clientId));
  builder.append("\" to access your protected resources?</p>");
  builder.append("<form id=\"confirmationForm\" name=\"confirmationForm\" action=\"");
  String requestPath=ServletUriComponentsBuilder.fromContextPath(request).build().getPath();
  if (requestPath == null) {
    requestPath="";
  }
  builder.append(requestPath).append("/oauth/authorize\" method=\"post\">");
  builder.append("<input name=\"user_oauth_approval\" value=\"true\" type=\"hidden\"/>");
  String csrfTemplate=null;
  CsrfToken csrfToken=(CsrfToken)(model.containsKey("_csrf") ? model.get("_csrf") : request.getAttribute("_csrf"));
  if (csrfToken != null) {
    csrfTemplate="<input type=\"hidden\" name=\"" + HtmlUtils.htmlEscape(csrfToken.getParameterName()) + "\" value=\"" + HtmlUtils.htmlEscape(csrfToken.getToken()) + "\" />";
  }
  if (csrfTemplate != null) {
    builder.append(csrfTemplate);
  }
  String authorizeInputTemplate="<label><input name=\"authorize\" value=\"Authorize\" type=\"submit\"/></label></form>";
  if (model.containsKey("scopes") || request.getAttribute("scopes") != null) {
    builder.append(createScopes(model,request));
    builder.append(authorizeInputTemplate);
  }
 else {
    builder.append(authorizeInputTemplate);
    builder.append("<form id=\"denialForm\" name=\"denialForm\" action=\"");
    builder.append(requestPath).append("/oauth/authorize\" method=\"post\">");
    builder.append("<input name=\"user_oauth_approval\" value=\"false\" type=\"hidden\"/>");
    if (csrfTemplate != null) {
      builder.append(csrfTemplate);
    }
    builder.append("<label><input name=\"deny\" value=\"Deny\" type=\"submit\"/></label></form>");
  }
  builder.append("</body></html>");
  return builder.toString();
}
