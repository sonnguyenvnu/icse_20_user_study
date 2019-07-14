@GetMapping("/authentication/require") @ResponseStatus(HttpStatus.UNAUTHORIZED) public String requireAuthentication(HttpServletRequest request,HttpServletResponse response) throws IOException {
  SavedRequest savedRequest=requestCache.getRequest(request,response);
  if (savedRequest != null) {
    String targetUrl=savedRequest.getRedirectUrl();
    if (StringUtils.endsWithIgnoreCase(targetUrl,".html"))     redirectStrategy.sendRedirect(request,response,"/login.html");
  }
  return "????????????";
}
