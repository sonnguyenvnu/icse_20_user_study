public static Trades adaptVaultoroTransactions(List<VaultoroTrade> vaultoroTransactions,CurrencyPair currencyPair){
  List<Trade> trades=new ArrayList<>();
  for (  VaultoroTrade vaultoroTrade : vaultoroTransactions) {
    Date date=VaultoroUtils.parseDate(vaultoroTrade.getTime());
    trades.add(new Trade.Builder().timestamp(date).currencyPair(currencyPair).price(vaultoroTrade.getGoldPrice()).originalAmount(vaultoroTrade.getGoldAmount()).build());
  }
  return new Trades(trades,TradeSortType.SortByTimestamp);
}
