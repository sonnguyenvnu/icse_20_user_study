public BitfinexTicker[] getBitfinexTickers(List<CurrencyPair> currencyPairs) throws IOException {
  return bitfinex.getTickers(BitfinexAdapters.adaptCurrencyPairsToTickersParam(currencyPairs));
}
