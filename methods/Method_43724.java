protected ExchangeException handleError(CoinbaseProException exception){
  if (exception.getMessage().contains("Insufficient")) {
    return new FundsExceededException(exception);
  }
 else   if (exception.getMessage().contains("Rate limit exceeded")) {
    return new RateLimitExceededException(exception);
  }
 else   if (exception.getMessage().contains("Internal server error")) {
    return new InternalServerException(exception);
  }
 else {
    return new ExchangeException(exception);
  }
}
