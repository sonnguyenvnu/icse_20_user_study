private OrderStatus mapOrderStatus(String state){
switch (state) {
case "wait":
    return OrderStatus.PENDING_NEW;
case "done":
  return OrderStatus.FILLED;
case "cancel":
return OrderStatus.CANCELED;
}
return null;
}
