public static List<FloatingRateLoanOrder> adaptFloatingRateLoanOrders(BitfinexLendLevel[] orders,String currency,String orderType,String id){
  List<FloatingRateLoanOrder> loanOrders=new ArrayList<>(orders.length);
  for (  BitfinexLendLevel order : orders) {
    if ("no".equals(order.getFrr())) {
      continue;
    }
    if (orderType.equalsIgnoreCase("loan")) {
      loanOrders.add(0,adaptFloatingRateLoanOrder(currency,order.getAmount(),order.getPeriod(),orderType,id,order.getRate()));
    }
 else {
      loanOrders.add(adaptFloatingRateLoanOrder(currency,order.getAmount(),order.getPeriod(),orderType,id,order.getRate()));
    }
  }
  return loanOrders;
}
