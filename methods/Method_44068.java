private static ExchangeException adaptBasedOnErrorMessage(DragonexException e){
  String message=e.getError();
  if (StringUtils.isEmpty(message)) {
    return new ExchangeException("Operation failed without any error message");
  }
  if (message.startsWith(INVALID_CURRENCY_MESSAGE_START)) {
    return new CurrencyPairNotValidException(message);
  }
  return new ExchangeException(message,e);
}
