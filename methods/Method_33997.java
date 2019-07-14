public void rememberTokens(Map<String,OAuthConsumerToken> tokens,HttpServletRequest request,HttpServletResponse response){
  HttpSession session=request.getSession(false);
  if (session == null) {
    return;
  }
  Map<String,OAuthConsumerToken> requestTokensOnly=new HashMap<String,OAuthConsumerToken>();
  for (  Map.Entry<String,OAuthConsumerToken> token : tokens.entrySet()) {
    if (storeAccessTokens && !token.getValue().isAccessToken())     requestTokensOnly.put(token.getKey(),token.getValue());
  }
  session.setAttribute(REMEMBERED_TOKENS_KEY,requestTokensOnly);
}
