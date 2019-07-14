public void checkBalance(LimitOrder order){
  checkBalance(order,order.getOriginalAmount().multiply(order.getLimitPrice()));
}
