@Override public Map<CurrencyPair,Fee> getDynamicTradingFees() throws IOException {
  Map<CurrencyPair,Fee> tradingFees=new HashMap<>();
  List<CurrencyPair> pairs=exchange.getExchangeSymbols();
  pairs.forEach(pair -> {
    try {
      BitflyerTradingCommission commission=getTradingCommission(BitflyerUtils.bitflyerProductCode(pair));
      tradingFees.put(pair,BitflyerAdapters.adaptTradingCommission(commission));
    }
 catch (    IOException|BitflyerException|ExchangeException e) {
      LOG.trace("Exception fetching trade commission for {}",pair,e);
    }
  }
);
  return tradingFees;
}
