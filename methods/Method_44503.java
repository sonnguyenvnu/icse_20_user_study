private UserTrades convertUserTrades(List<TradeResponse> fills){
  return new UserTrades(fills.stream().map(KucoinAdapters::adaptUserTrade).collect(toCollection(ArrayList::new)),TradeSortType.SortByTimestamp);
}
