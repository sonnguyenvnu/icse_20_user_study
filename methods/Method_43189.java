public static String fromOrderType(Order.OrderType type){
switch (type) {
case BID:
    return "bid";
case ASK:
  return "ask";
default :
throw new NotAvailableFromExchangeException();
}
}
