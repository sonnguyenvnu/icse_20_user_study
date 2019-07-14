public TheRockOrder showTheRockOrder(CurrencyPair currencyPair,Long orderId) throws TheRockException, IOException {
  try {
    return theRockAuthenticated.showOrder(new TheRock.Pair(currencyPair),orderId,exchange.getExchangeSpecification().getApiKey(),signatureCreator,exchange.getNonceFactory());
  }
 catch (  TheRockException e) {
    throw new ExchangeException(e);
  }
}
