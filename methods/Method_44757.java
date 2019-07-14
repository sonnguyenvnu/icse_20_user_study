public PoloniexMarginPostionResponse returnMarginPosition(CurrencyPair currencyPair) throws IOException {
  return poloniexAuthenticated.getMarginPosition(apiKey,signatureCreator,exchange.getNonceFactory(),PoloniexUtils.toPairString(currencyPair));
}
