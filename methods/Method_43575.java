/** 
 * CexIO position status is not documented, testing API we can infer that they are similar to order status  {@link #adaptOrderStatus(CexIOOpenOrder)}
 * @param cexioPosition cex raw order
 * @return OrderStatus
 */
public static Order.OrderStatus adaptPositionStatus(CexioPosition cexioPosition){
  if ("c".equalsIgnoreCase(cexioPosition.getStatus()))   return Order.OrderStatus.CANCELED;
  if ("d".equalsIgnoreCase(cexioPosition.getStatus()))   return Order.OrderStatus.FILLED;
  if ("a".equalsIgnoreCase(cexioPosition.getStatus()))   return Order.OrderStatus.NEW;
  return Order.OrderStatus.UNKNOWN;
}
