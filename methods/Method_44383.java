private static Order.OrderStatus adaptOrderStatus(String orderStatus){
switch (orderStatus) {
case "Open":
    return Order.OrderStatus.NEW;
case "PartiallyFilled":
  return Order.OrderStatus.PARTIALLY_FILLED;
case "Filled":
return Order.OrderStatus.FILLED;
case "PartiallyFilledAndCancelled":
return Order.OrderStatus.PARTIALLY_CANCELED;
case "Cancelled":
return Order.OrderStatus.CANCELED;
case "PartiallyFilledAndExpired":
return Order.OrderStatus.EXPIRED;
case "Expired":
return Order.OrderStatus.EXPIRED;
default :
throw new IllegalStateException("Unknown status found in Independent Reserve : " + orderStatus);
}
}
