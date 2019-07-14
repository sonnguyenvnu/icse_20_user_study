private CharSequence createScopes(Map<String,Object> model,HttpServletRequest request){
  StringBuilder builder=new StringBuilder("<ul>");
  @SuppressWarnings("unchecked") Map<String,String> scopes=(Map<String,String>)(model.containsKey("scopes") ? model.get("scopes") : request.getAttribute("scopes"));
  for (  String scope : scopes.keySet()) {
    String approved="true".equals(scopes.get(scope)) ? " checked" : "";
    String denied=!"true".equals(scopes.get(scope)) ? " checked" : "";
    scope=HtmlUtils.htmlEscape(scope);
    builder.append("<li><div class=\"form-group\">");
    builder.append(scope).append(": <input type=\"radio\" name=\"");
    builder.append(scope).append("\" value=\"true\"").append(approved).append(">Approve</input> ");
    builder.append("<input type=\"radio\" name=\"").append(scope).append("\" value=\"false\"");
    builder.append(denied).append(">Deny</input></div></li>");
  }
  builder.append("</ul>");
  return builder.toString();
}
