private static OrderStatus adaptOrderStatus(BittrexOpenOrder order){
  OrderStatus status=OrderStatus.NEW;
  BigDecimal qty=order.getQuantity();
  BigDecimal qtyRem=order.getQuantityRemaining() != null ? order.getQuantityRemaining() : order.getQuantity();
  Boolean isCancelling=order.getCancelInitiated();
  int qtyRemainingToQty=qtyRem.compareTo(qty);
  if (!isCancelling && qtyRemainingToQty < 0) {
    status=OrderStatus.PARTIALLY_FILLED;
  }
 else   if (isCancelling) {
    status=OrderStatus.PENDING_CANCEL;
  }
  return status;
}
