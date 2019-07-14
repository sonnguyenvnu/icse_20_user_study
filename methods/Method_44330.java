public List<HitbtcCandle> getHitbtcCandles(CurrencyPair currencyPair,int limit,String period,int offset,String sort) throws IOException {
  return hitbtc.getHitbtcOHLC(HitbtcAdapters.adaptCurrencyPair(currencyPair),limit,period,offset,sort);
}
