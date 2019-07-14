private static Order.OrderType adaptTransactionSearch(String search){
switch (search) {
case BithumbTransaction.SEARCH_BUY:
    return Order.OrderType.BID;
case BithumbTransaction.SEARCH_SELL:
  return Order.OrderType.ASK;
default :
return null;
}
}
