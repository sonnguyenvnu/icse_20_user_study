public static List<FloatingRateLoanOrder> adaptFloatingRateLoanOrders(GeminiLendLevel[] orders,String currency,String orderType,String id){
  List<FloatingRateLoanOrder> loanOrders=new ArrayList<>(orders.length);
  for (  GeminiLendLevel order : orders) {
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
