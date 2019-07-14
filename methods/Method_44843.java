public void checkBalance(Order order,BigDecimal bidAmount){
switch (order.getType()) {
case ASK:
    BigDecimal askAmount=order.getRemainingAmount();
  Balance askBalance=balances.computeIfAbsent(order.getCurrencyPair().base,this::defaultBalance).get();
checkBalance(order,askAmount,askBalance);
break;
case BID:
Balance bidBalance=balances.computeIfAbsent(order.getCurrencyPair().counter,this::defaultBalance).get();
checkBalance(order,bidAmount,bidBalance);
break;
default :
throw new NotAvailableFromExchangeException("Order type " + order.getType() + " not supported");
}
}
