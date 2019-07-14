/** 
 * Generates new CSRF token and puts it in the session. Returns generated token value.
 */
@SuppressWarnings({"unchecked"}) public static String prepareCsrfToken(final HttpSession session,final int timeToLive){
  Set<Token> tokenSet=(Set<Token>)session.getAttribute(CSRF_TOKEN_SET);
  if (tokenSet == null) {
    tokenSet=new HashSet<>();
    session.setAttribute(CSRF_TOKEN_SET,tokenSet);
  }
  String value;
  boolean unique;
  do {
    value=RandomString.get().randomAlphaNumeric(32);
    assureSize(tokenSet);
    unique=tokenSet.add(new Token(value,timeToLive));
  }
 while (!unique);
  return value;
}
