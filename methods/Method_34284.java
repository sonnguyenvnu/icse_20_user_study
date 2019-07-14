@ExceptionHandler(HttpRequestMethodNotSupportedException.class) public ResponseEntity<OAuth2Exception> handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e) throws Exception {
  if (logger.isInfoEnabled()) {
    logger.info("Handling error: " + e.getClass().getSimpleName() + ", " + e.getMessage());
  }
  return getExceptionTranslator().translate(e);
}
