@ExceptionHandler(ClientRegistrationException.class) public ResponseEntity<OAuth2Exception> handleClientRegistrationException(Exception e) throws Exception {
  if (logger.isWarnEnabled()) {
    logger.warn("Handling error: " + e.getClass().getSimpleName() + ", " + e.getMessage());
  }
  return getExceptionTranslator().translate(new BadClientCredentialsException());
}
