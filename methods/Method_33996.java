@SuppressWarnings("unchecked") public Map<String,OAuthConsumerToken> loadRememberedTokens(HttpServletRequest request,HttpServletResponse response){
  HttpSession session=request.getSession(false);
  if (session != null) {
    return (Map<String,OAuthConsumerToken>)session.getAttribute(REMEMBERED_TOKENS_KEY);
  }
  return null;
}
