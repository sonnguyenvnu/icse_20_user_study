/** 
 * *****Adapter for LykkeOpenOrders***** 
 */
public static List<LimitOrder> adaptOpenOrders(List<CurrencyPair> currencyPairList,List<LykkeOrder> lykkeOrders) throws IOException {
  List<LimitOrder> limitOrders=new ArrayList<>();
  for (  LykkeOrder lykkeOrder : lykkeOrders) {
    limitOrders.add(adaptLimitOrder(currencyPairList,lykkeOrder));
  }
  return limitOrders;
}
