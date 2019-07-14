@Override public void onAuthenticationSuccess(HttpServletRequest request,HttpServletResponse response,Authentication authentication) throws IOException, ServletException {
  if (LOG.isDebugEnabled()) {
    LOG.debug("Processing successful authentication successful");
  }
  String callbackURL=(String)request.getAttribute(CALLBACK_ATTRIBUTE);
  if (callbackURL == null) {
    if (!isRequire10a()) {
      callbackURL=request.getParameter(getCallbackParameterName());
      if (callbackURL == null) {
        callbackURL="oob";
      }
    }
 else {
      throw new IllegalStateException("Callback URL was not loaded into the request. attemptAuthentication() never called?");
    }
  }
  if ("oob".equals(callbackURL)) {
    callbackURL=super.determineTargetUrl(request,response);
  }
  String requestToken=request.getParameter(getTokenParameterName());
  char appendChar='?';
  if (callbackURL.indexOf('?') > 0) {
    appendChar='&';
  }
  String verifier=(String)request.getAttribute(VERIFIER_ATTRIBUTE);
  String targetUrl=new StringBuilder(callbackURL).append(appendChar).append("oauth_token=").append(requestToken).append("&oauth_verifier=").append(verifier).toString();
  getRedirectStrategy().sendRedirect(request,response,targetUrl);
}
