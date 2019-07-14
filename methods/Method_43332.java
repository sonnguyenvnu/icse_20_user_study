private static OrderStatus adaptOrderStatus(BittrexOrder order){
  OrderStatus status=OrderStatus.NEW;
  BigDecimal qty=order.getQuantity();
  BigDecimal qtyRem=order.getQuantityRemaining() != null ? order.getQuantityRemaining() : order.getQuantity();
  Boolean isOpen=order.getOpen();
  Boolean isCancelling=order.getCancelInitiated();
  int qtyRemainingToQty=qtyRem.compareTo(qty);
  int qtyRemainingIsZero=qtyRem.compareTo(BigDecimal.ZERO);
  if (isOpen && !isCancelling && qtyRemainingToQty < 0) {
    status=OrderStatus.PARTIALLY_FILLED;
  }
 else   if (!isOpen && !isCancelling && qtyRemainingIsZero <= 0) {
    status=OrderStatus.FILLED;
  }
 else   if (isOpen && isCancelling) {
    status=OrderStatus.PENDING_CANCEL;
  }
 else   if (!isOpen && isCancelling) {
    status=OrderStatus.CANCELED;
  }
  return status;
}
