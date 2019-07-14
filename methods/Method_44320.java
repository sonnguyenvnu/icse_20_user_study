/** 
 * Decodes HitBTC Order status.
 * @return
 * @see https://api.hitbtc.com/#order-model Order Model possible statuses: new, suspended,partiallyFilled, filled, canceled, expired
 */
private static Order.OrderStatus convertOrderStatus(String status){
switch (status) {
case "new":
    return Order.OrderStatus.NEW;
case "suspended":
  return Order.OrderStatus.STOPPED;
case "partiallyFilled":
return Order.OrderStatus.PARTIALLY_FILLED;
case "filled":
return Order.OrderStatus.FILLED;
case "canceled":
return Order.OrderStatus.CANCELED;
case "expired":
return Order.OrderStatus.EXPIRED;
default :
throw new RuntimeException("Unknown HitBTC transaction status: " + status);
}
}
