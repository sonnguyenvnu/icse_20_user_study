public BithumbTradeResponse placeBithumbMarketOrder(MarketOrder marketOrder) throws IOException {
switch (marketOrder.getType()) {
case BID:
    return bithumbAuthenticated.marketBuy(apiKey,signatureCreator,exchange.getNonceFactory(),"2",endpointGenerator,marketOrder.getOriginalAmount(),BithumbUtils.getBaseCurrency(marketOrder.getCurrencyPair()));
case ASK:
  return bithumbAuthenticated.marketSell(apiKey,signatureCreator,exchange.getNonceFactory(),"2",endpointGenerator,marketOrder.getOriginalAmount(),BithumbUtils.getBaseCurrency(marketOrder.getCurrencyPair()));
default :
throw new NotAvailableFromExchangeException();
}
}
