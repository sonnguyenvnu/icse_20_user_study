public static ExchangeException adapt(BityException e){
  String message=e.getMessage();
  if (StringUtils.isEmpty(message)) {
    return new ExchangeException("Operation failed without any error message");
  }
switch (message) {
case "INVALID_MARKET":
    return new CurrencyPairNotValidException();
}
return new ExchangeException(message);
}
