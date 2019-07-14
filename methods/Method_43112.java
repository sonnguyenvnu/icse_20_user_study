@Override public Map<CurrencyPair,Fee> getDynamicTradingFees() throws IOException {
  try {
    List<CurrencyPair> allCurrencyPairs=exchange.getExchangeSymbols();
    return BitfinexAdapters.adaptDynamicTradingFees(getBitfinexDynamicTradingFees(),allCurrencyPairs);
  }
 catch (  BitfinexException e) {
    throw BitfinexErrorAdapter.adapt(e);
  }
}
