public static List<LimitOrder> createOrders(CurrencyPair currencyPair,Order.OrderType orderType,List<CCEXBuySellData> orders){
  List<LimitOrder> limitOrders=new ArrayList<>();
  if (orders == null) {
    return new ArrayList<>();
  }
  for (  CCEXBuySellData ask : orders) {
    limitOrders.add(createOrder(currencyPair,ask,orderType));
  }
  return limitOrders;
}
