private void reserve(LimitOrder order,boolean negate){
switch (order.getType()) {
case ASK:
    BigDecimal askAmount=negate ? order.getRemainingAmount().negate() : order.getRemainingAmount();
  balance(order.getCurrencyPair().base).updateAndGet(b -> {
    if (b.getAvailable().compareTo(askAmount) < 0) {
      throw new ExchangeException("Insufficient balance: " + askAmount.toPlainString() + order.getCurrencyPair().base + " required but only " + b.getAvailable() + " available");
    }
    return Balance.Builder.from(b).available(b.getAvailable().subtract(askAmount)).frozen(b.getFrozen().add(askAmount)).build();
  }
);
break;
case BID:
BigDecimal bid=order.getRemainingAmount().multiply(order.getLimitPrice());
BigDecimal bidAmount=negate ? bid.negate() : bid;
balance(order.getCurrencyPair().counter).updateAndGet(b -> {
if (b.getAvailable().compareTo(bidAmount) < 0) {
throw new ExchangeException("Insufficient balance: " + bidAmount.toPlainString() + order.getCurrencyPair().counter + " required but only " + b.getAvailable() + " available");
}
return Balance.Builder.from(b).available(b.getAvailable().subtract(bidAmount)).frozen(b.getFrozen().add(bidAmount)).build();
}
);
break;
default :
throw new NotAvailableFromExchangeException("Order type " + order.getType() + " not supported");
}
}
