private static Order.OrderType adapeOrderType(String orderType){
switch (orderType) {
case "LimitOffer":
case "MarketOffer":
    return Order.OrderType.ASK;
case "LimitBid":
case "MarketBid":
  return Order.OrderType.BID;
default :
throw new IllegalStateException("Unknown order found in Independent Reserve : " + orderType);
}
}
