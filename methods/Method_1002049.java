public User provide(ContainerRequest request){
  Cookie sessionCookie=request.getCookies().get(sessionCookieName);
  if (sessionCookie == null) {
    logger.warn("No session cookie in request.");
    throw new NotAuthorizedException("Bad session");
  }
  try {
    return authenticator.authenticate(sessionCookie).orElseThrow(() -> new NotAuthorizedException("Bad session"));
  }
 catch (  AuthenticationException e) {
    throw Throwables.propagate(e);
  }
}
