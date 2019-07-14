public static String toBl3pOrderType(Order.OrderType orderType){
switch (orderType) {
case BID:
    return "bid";
case ASK:
  return "ask";
case EXIT_ASK:
return "exit_ask";
case EXIT_BID:
return "exit_bid";
default :
return null;
}
}
