private void checkBalance(Order order,Account account){
  if (order instanceof LimitOrder) {
    account.checkBalance((LimitOrder)order);
  }
 else {
    BigDecimal marketCostOrProceeds=marketCostOrProceeds(order.getType(),order.getOriginalAmount());
    BigDecimal marketAmount=order.getType().equals(OrderType.BID) ? marketCostOrProceeds : order.getOriginalAmount();
    account.checkBalance(order,marketAmount);
  }
}
