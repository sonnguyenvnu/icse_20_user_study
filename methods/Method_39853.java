/** 
 * Removes expired tokens if token set is full.
 * @see #setMaxTokensPerSession(int)  
 */
protected static void assureSize(final Set<Token> tokenSet){
  if (tokenSet.size() < maxTokensPerSession) {
    return;
  }
  long validUntilMin=Long.MAX_VALUE;
  Token tokenToRemove=null;
  Iterator<Token> iterator=tokenSet.iterator();
  while (iterator.hasNext()) {
    Token token=iterator.next();
    if (token.isExpired()) {
      iterator.remove();
      continue;
    }
    if (token.validUntil < validUntilMin) {
      validUntilMin=token.validUntil;
      tokenToRemove=token;
    }
  }
  if ((tokenToRemove != null) && (tokenSet.size() >= maxTokensPerSession)) {
    tokenSet.remove(tokenToRemove);
  }
}
