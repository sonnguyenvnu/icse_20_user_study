@Override public void decide(Authentication authentication,Object object,Collection<ConfigAttribute> configAttributes) throws AccessDeniedException, InsufficientAuthenticationException {
  if (null == configAttributes || configAttributes.size() <= 0) {
    return;
  }
  ConfigAttribute c;
  String needRole;
  for (Iterator<ConfigAttribute> iter=configAttributes.iterator(); iter.hasNext(); ) {
    c=iter.next();
    needRole=c.getAttribute();
    for (    GrantedAuthority ga : authentication.getAuthorities()) {
      if (needRole.trim().equals(ga.getAuthority())) {
        return;
      }
    }
  }
  throw new AccessDeniedException("no right");
}
