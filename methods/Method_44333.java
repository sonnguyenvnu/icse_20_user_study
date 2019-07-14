public List<HitbtcOwnTrade> getTradeHistoryRaw(String symbol,Integer limit,long offset) throws IOException {
  return hitbtc.getHitbtcTrades(symbol,null,null,null,null,limit,offset);
}
