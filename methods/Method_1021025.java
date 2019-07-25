@Override public AuthorizeResult authorize(){
  return SecurityUtils.getSubject().getPrincipal() == null ? AuthorizeResult.fail(AuthorizeResult.ERROR_CODE_UNAUTHENTICATED) : AuthorizeResult.ok();
}
