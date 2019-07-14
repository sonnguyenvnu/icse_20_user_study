public List<CoingiTicker> getTickers(CurrencyPair currencyPair,Integer aggregationIntervalSize,int maxCount) throws IOException {
  return coingi.getTicker(CoingiAdapters.adaptCurrency(currencyPair),aggregationIntervalSize,maxCount);
}
