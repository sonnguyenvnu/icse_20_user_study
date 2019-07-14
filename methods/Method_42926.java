public static UserTrades adaptUserTrades(BiboxOrders biboxOrderHistory){
  List<UserTrade> trades=biboxOrderHistory.getItems().stream().map(BiboxAdapters::adaptUserTrade).collect(Collectors.toList());
  return new UserTrades(trades,TradeSortType.SortByID);
}
