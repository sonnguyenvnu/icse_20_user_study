public CexioClosePosition closePosition(CurrencyPair currencyPair,String id) throws IOException {
  CexioClosePositionResponse response=cexIOAuthenticated.closePosition(signatureCreator,currencyPair.base.getSymbol(),currencyPair.counter.getSymbol(),new CexIOGetPositionRequest(id));
  if (!"ok".equalsIgnoreCase(response.getStatus())) {
    throw new ExchangeException(response.getEventName() + " " + response.getStatus());
  }
  return response.getPosition();
}
