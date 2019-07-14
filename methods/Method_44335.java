public List<HitbtcOwnTrade> getTradeHistoryRaw(String symbol,HitbtcSort sort,Long fromId,Long tillId,Integer limit,long offset) throws IOException {
  String sortValue=sort != null ? sort.toString().toUpperCase() : null;
  String fromValue=fromId != null ? fromId.toString() : null;
  String tillValue=tillId != null ? tillId.toString() : null;
  return hitbtc.getHitbtcTrades(symbol,sortValue,"id",fromValue,tillValue,limit,offset);
}
