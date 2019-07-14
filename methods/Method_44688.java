public OkCoinPriceLimit getFuturesPriceLimits(CurrencyPair currencyPair,FuturesContract prompt) throws IOException {
  return okCoin.getFuturesPriceLimits(OkCoinAdapters.adaptSymbol(currencyPair),prompt.getName());
}
