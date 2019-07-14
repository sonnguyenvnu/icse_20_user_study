/** 
 * Checks token value. C	 
 */
@SuppressWarnings({"unchecked"}) public static boolean checkCsrfToken(final HttpSession session,final String tokenValue){
  Set<Token> tokenSet=(Set<Token>)session.getAttribute(CSRF_TOKEN_SET);
  if ((tokenSet == null) && (tokenValue == null)) {
    return true;
  }
  if ((tokenSet == null) || (tokenValue == null)) {
    return false;
  }
  boolean found=false;
  Iterator<Token> it=tokenSet.iterator();
  while (it.hasNext()) {
    Token t=it.next();
    if (t.isExpired()) {
      it.remove();
      continue;
    }
    if (t.getValue().equals(tokenValue)) {
      it.remove();
      found=true;
    }
  }
  return found;
}
