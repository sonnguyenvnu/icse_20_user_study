protected ExchangeException handleError(CryptonitException exception){
  if (exception.getMessage().contains("You can only buy")) {
    return new FundsExceededException(exception);
  }
 else   if (exception.getMessage().contains("Invalid limit exceeded")) {
    return new RateLimitExceededException(exception);
  }
 else   if (exception.getMessage().contains("Invalid nonce")) {
    return new NonceException(exception.getMessage());
  }
 else   if (exception.getMessage().contains("Internal server error")) {
    return new InternalServerException(exception);
  }
 else {
    return new ExchangeException(exception);
  }
}
