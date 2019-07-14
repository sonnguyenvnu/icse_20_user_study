@Override public boolean doCredentialsMatch(AuthenticationToken token,AuthenticationInfo info){
  String username=(String)token.getPrincipal();
  AtomicInteger retryCount=passwordRetryCache.get(username);
  if (retryCount == null) {
    retryCount=new AtomicInteger(0);
    passwordRetryCache.put(username,retryCount);
  }
  if (retryCount.incrementAndGet() > 5) {
    throw new ExcessiveAttemptsException();
  }
  boolean matches=super.doCredentialsMatch(token,info);
  if (matches) {
    passwordRetryCache.remove(username);
    PmsOperator operator=pmsOperatorService.findOperatorByLoginName(username);
    Subject subject=SecurityUtils.getSubject();
    Session session=subject.getSession();
    session.setAttribute("PmsOperator",operator);
  }
  return matches;
}
