public static List<UserTrade> adaptUserTrades(List<BittrexUserTrade> bittrexUserTrades){
  List<UserTrade> trades=new ArrayList<>();
  for (  BittrexUserTrade bittrexTrade : bittrexUserTrades) {
    if (!isOrderWithoutTrade(bittrexTrade)) {
      trades.add(adaptUserTrade(bittrexTrade));
    }
  }
  return trades;
}
