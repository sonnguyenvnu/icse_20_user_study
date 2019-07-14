public String getOrderType(OrderType type){
switch (type) {
case BID:
    return "buy";
case ASK:
  return "sell";
}
throw new IllegalArgumentException("Unknown order type: " + type);
}
