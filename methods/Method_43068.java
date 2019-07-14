public static Trades adaptBitcointoyouPublicTrades(BitcointoyouPublicTrade[] bitcointoyouPublicTrades,CurrencyPair currencyPair){
  List<Trade> trades=new ArrayList<>();
  for (  BitcointoyouPublicTrade bitcointoyouTrade : bitcointoyouPublicTrades) {
    trades.add(adaptBitcointoyouPublicTrade(bitcointoyouTrade,currencyPair));
  }
  return new Trades(trades,TradeSortType.SortByTimestamp);
}
