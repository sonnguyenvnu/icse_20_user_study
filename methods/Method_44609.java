/** 
 * **Adapter for LykkeTradeHistory*** 
 */
public static List<UserTrade> adaptUserTrades(List<CurrencyPair> currencyPairList,List<LykkeOrder> tradeHistoryList) throws IOException {
  List<UserTrade> userTrades=new ArrayList<>();
  for (  LykkeOrder lykkeTradeHistory : tradeHistoryList) {
    userTrades.add(adaptUserTrade(currencyPairList,lykkeTradeHistory));
  }
  return userTrades;
}
