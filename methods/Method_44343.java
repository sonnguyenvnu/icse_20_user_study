private static OrderStatus adaptOrderStatus(String huobiStatus){
  OrderStatus result=OrderStatus.UNKNOWN;
switch (huobiStatus) {
case "pre-submitted":
    result=OrderStatus.PENDING_NEW;
  break;
case "submitting":
result=OrderStatus.PENDING_NEW;
break;
case "submitted":
result=OrderStatus.NEW;
break;
case "partial-filled":
result=OrderStatus.PARTIALLY_FILLED;
break;
case "partial-canceled":
result=OrderStatus.PARTIALLY_CANCELED;
break;
case "filled":
result=OrderStatus.FILLED;
break;
case "canceled":
result=OrderStatus.CANCELED;
break;
}
return result;
}
