/** 
 * Find and match an order with id in orders
 * @param orders
 * @param order
 * @param id
 * @return
 */
public static boolean findLimitOrder(List<LimitOrder> orders,LimitOrder order,String id){
  boolean found=false;
  for (  LimitOrder openOrder : orders) {
    if (openOrder.getId().equalsIgnoreCase(id)) {
      if (order.getCurrencyPair().equals(openOrder.getCurrencyPair()) && (order.getOriginalAmount().compareTo(openOrder.getOriginalAmount()) == 0) && (order.getLimitPrice().compareTo(openOrder.getLimitPrice()) == 0)) {
        found=true;
      }
    }
  }
  return found;
}
