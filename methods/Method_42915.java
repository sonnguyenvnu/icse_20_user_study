public static ExchangeException adaptError(BankeraException exception){
  return exception.getHttpStatusCode() == 403 ? new ExchangeSecurityException() : new ExchangeException(exception.getError(),exception);
}
