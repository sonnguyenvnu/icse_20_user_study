public static UserTrades adaptFills(CryptoFacilitiesFills cryptoFacilitiesFills){
  List<UserTrade> trades=new ArrayList<>();
  if (cryptoFacilitiesFills != null && cryptoFacilitiesFills.isSuccess()) {
    for (    CryptoFacilitiesFill fill : cryptoFacilitiesFills.getFills()) {
      trades.add(adaptFill(fill));
    }
  }
  return new UserTrades(trades,TradeSortType.SortByTimestamp);
}
