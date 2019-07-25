@Override public AuthorizeResult authorize(){
  return !SecurityUtils.getSubject().isAuthenticated() ? AuthorizeResult.fail(AuthorizeResult.ERROR_CODE_UNAUTHENTICATED) : AuthorizeResult.ok();
}
