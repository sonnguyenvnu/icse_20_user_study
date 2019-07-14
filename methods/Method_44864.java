public void cancelOrder(String orderId,Order.OrderType type){
switch (type) {
case ASK:
    asks.stream().forEach(bookLevel -> bookLevel.getOrders().removeIf(bookOrder -> bookOrder.getId().equals(orderId)));
  break;
case BID:
bids.stream().forEach(bookLevel -> bookLevel.getOrders().removeIf(bookOrder -> bookOrder.getId().equals(orderId)));
break;
default :
throw new ExchangeException("Unsupported order type: " + type);
}
}
