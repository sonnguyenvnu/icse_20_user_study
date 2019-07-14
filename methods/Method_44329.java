public List<HitbtcCandle> getHitbtcCandles(CurrencyPair currencyPair,int limit,String period,Date from,Date till,String sort) throws IOException {
  return hitbtc.getHitbtcOHLC(HitbtcAdapters.adaptCurrencyPair(currencyPair),limit,period,from,till,sort);
}
