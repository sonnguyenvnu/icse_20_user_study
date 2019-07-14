@RequestMapping("/oauth/confirm_access") public ModelAndView getAccessConfirmation(HttpServletRequest request,HttpServletResponse response) throws Exception {
  String token=request.getParameter("oauth_token");
  if (token == null) {
    throw new IllegalArgumentException("A request token to authorize must be provided.");
  }
  OAuthProviderToken providerToken=tokenServices.getToken(token);
  ConsumerDetails consumer=consumerDetailsService.loadConsumerByConsumerKey(providerToken.getConsumerKey());
  String callback=request.getParameter("oauth_callback");
  TreeMap<String,Object> model=new TreeMap<String,Object>();
  model.put("oauth_token",token);
  if (callback != null) {
    model.put("oauth_callback",callback);
  }
  model.put("consumer",consumer);
  return new ModelAndView("access_confirmation",model);
}
