protected RuntimeException handleError(BitcoindeException exception){
  if (exception.getMessage().contains("Insufficient credits")) {
    return new RateLimitExceededException(exception);
  }
 else {
    return new ExchangeException(exception.getMessage(),exception);
  }
}
