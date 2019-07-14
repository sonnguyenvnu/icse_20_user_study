/** 
 * Redirect the user according to the specified exception.
 * @param e The user redirect exception.
 * @param request The request.
 * @param response The response.
 */
protected void redirectUser(UserRedirectRequiredException e,HttpServletRequest request,HttpServletResponse response) throws IOException {
  String redirectUri=e.getRedirectUri();
  UriComponentsBuilder builder=UriComponentsBuilder.fromHttpUrl(redirectUri);
  Map<String,String> requestParams=e.getRequestParams();
  for (  Map.Entry<String,String> param : requestParams.entrySet()) {
    builder.queryParam(param.getKey(),param.getValue());
  }
  if (e.getStateKey() != null) {
    builder.queryParam("state",e.getStateKey());
  }
  this.redirectStrategy.sendRedirect(request,response,builder.build().encode().toUriString());
}
