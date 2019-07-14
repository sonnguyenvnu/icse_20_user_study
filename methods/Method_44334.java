public List<HitbtcOwnTrade> getTradeHistoryRaw(String symbol,HitbtcSort sort,Date from,Date till,Integer limit,long offset) throws IOException {
  String sortValue=sort != null ? sort.toString().toUpperCase() : null;
  String fromValue=from != null ? Instant.ofEpochMilli(from.getTime()).toString() : null;
  String tillValue=till != null ? Instant.ofEpochMilli(till.getTime()).toString() : null;
  return hitbtc.getHitbtcTrades(symbol,sortValue,"timestamp",fromValue,tillValue,limit,offset);
}
