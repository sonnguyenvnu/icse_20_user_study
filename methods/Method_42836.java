public static OrderStatus adaptOrderStatus(AbucoinsOrder.Status status){
switch (status) {
case pending:
    return OrderStatus.PENDING_NEW;
case open:
  return OrderStatus.NEW;
case done:
return OrderStatus.FILLED;
case closed:
return OrderStatus.PARTIALLY_FILLED;
case rejected:
return OrderStatus.REJECTED;
default :
logger.warn("Unrecognized Status " + status + " returning null for OrderStatus");
return null;
}
}
