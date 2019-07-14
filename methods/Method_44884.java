public TheRockOrder placeTheRockOrder(CurrencyPair currencyPair,TheRockOrder order) throws ExchangeException, IOException {
  try {
    return theRockAuthenticated.placeOrder(new TheRock.Pair(currencyPair),exchange.getExchangeSpecification().getApiKey(),signatureCreator,exchange.getNonceFactory(),order);
  }
 catch (  TheRockException e) {
    throw new ExchangeException(e);
  }
}
