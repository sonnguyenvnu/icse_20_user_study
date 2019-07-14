protected ExchangeException handleError(Exception exception){
  if (exception != null && exception.getMessage() != null) {
    if (exception.getMessage().contains("Insufficient")) {
      return new FundsExceededException(exception);
    }
 else     if (exception.getMessage().contains("Rate limit exceeded")) {
      return new RateLimitExceededException(exception);
    }
 else     if (exception.getMessage().contains("Internal server error")) {
      return new InternalServerException(exception);
    }
 else     if (exception.getMessage().contains("The system is currently overloaded")) {
      return new SystemOverloadException(exception);
    }
 else {
      return new ExchangeException(exception.getMessage(),exception);
    }
  }
  return new ExchangeException(exception);
}
