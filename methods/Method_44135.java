protected ExchangeException handleException(DVChainException exception){
  if (exception.getMessage().contains("Internal server error")) {
    return new InternalServerException(exception);
  }
 else   if (exception.getMessage().contains("An unknown error")) {
    return new InternalServerException(exception);
  }
 else {
    return new ExchangeException(exception);
  }
}
