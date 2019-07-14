protected RuntimeException handleError(ANXException exception){
  if ("Insufficient Funds".equals(exception.getError())) {
    return new FundsExceededException(exception.getError(),exception);
  }
 else {
    return new ExchangeException(exception.getError(),exception);
  }
}
