public static String adaptOrderType(OrderType type){
switch (type) {
case BID:
case EXIT_BID:
    return "buy";
case ASK:
case EXIT_ASK:
  return "sell";
}
throw new IllegalArgumentException(String.format("Unexpected type of order: %s",type));
}
