public GlobitexExecutionReport placeGlobitexMarketOrder(MarketOrder marketOrder) throws IOException {
  try {
    return globitex.placeNewOrder(exchange.getExchangeSpecification().getApiKey(),exchange.getNonceFactory(),signatureCreator,exchange.getExchangeSpecification().getUserName(),GlobitexAdapters.adaptCurrencyPairToGlobitexSymbol(marketOrder.getCurrencyPair()),GlobitexAdapters.adaptOrderType(marketOrder.getType()),null,marketOrder.getOriginalAmount().toString());
  }
 catch (  HttpStatusIOException e) {
    throw new ExchangeException(e.getHttpBody());
  }
}
