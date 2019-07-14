public List<BittrexUserTrade> getBittrexTradeHistory(CurrencyPair currencyPair) throws IOException {
  String ccyPair=currencyPair == null ? null : BittrexUtils.toPairString(currencyPair);
  return bittrexAuthenticated.getorderhistory(apiKey,signatureCreator,exchange.getNonceFactory(),ccyPair).getResult();
}
