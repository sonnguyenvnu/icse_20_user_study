@Override public ResponseEntity<OAuth2Exception> translate(Exception e) throws Exception {
  Throwable[] causeChain=throwableAnalyzer.determineCauseChain(e);
  Exception ase=(OAuth2Exception)throwableAnalyzer.getFirstThrowableOfType(OAuth2Exception.class,causeChain);
  if (ase != null) {
    return handleOAuth2Exception((OAuth2Exception)ase);
  }
  ase=(AuthenticationException)throwableAnalyzer.getFirstThrowableOfType(AuthenticationException.class,causeChain);
  if (ase != null) {
    return handleOAuth2Exception(new UnauthorizedException(e.getMessage(),e));
  }
  ase=(AccessDeniedException)throwableAnalyzer.getFirstThrowableOfType(AccessDeniedException.class,causeChain);
  if (ase instanceof AccessDeniedException) {
    return handleOAuth2Exception(new ForbiddenException(ase.getMessage(),ase));
  }
  ase=(HttpRequestMethodNotSupportedException)throwableAnalyzer.getFirstThrowableOfType(HttpRequestMethodNotSupportedException.class,causeChain);
  if (ase instanceof HttpRequestMethodNotSupportedException) {
    return handleOAuth2Exception(new MethodNotAllowed(ase.getMessage(),ase));
  }
  return handleOAuth2Exception(new ServerErrorException(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),e));
}
