public void fill(UserTrade userTrade,boolean reserved){
  BigDecimal counterAmount=userTrade.getOriginalAmount().multiply(userTrade.getPrice());
switch (userTrade.getType()) {
case ASK:
    balance(userTrade.getCurrencyPair().base).updateAndGet(b -> Balance.Builder.from(b).available(reserved ? b.getAvailable() : b.getAvailable().subtract(userTrade.getOriginalAmount())).frozen(reserved ? b.getFrozen().subtract(userTrade.getOriginalAmount()) : b.getFrozen()).total(b.getTotal().subtract(userTrade.getOriginalAmount())).build());
  balance(userTrade.getCurrencyPair().counter).updateAndGet(b -> Balance.Builder.from(b).total(b.getTotal().add(counterAmount)).available(b.getAvailable().add(counterAmount)).build());
break;
case BID:
balance(userTrade.getCurrencyPair().base).updateAndGet(b -> Balance.Builder.from(b).total(b.getTotal().add(userTrade.getOriginalAmount())).available(b.getAvailable().add(userTrade.getOriginalAmount())).build());
balance(userTrade.getCurrencyPair().counter).updateAndGet(b -> Balance.Builder.from(b).available(reserved ? b.getAvailable() : b.getAvailable().subtract(counterAmount)).frozen(reserved ? b.getFrozen().subtract(counterAmount) : b.getFrozen()).total(b.getTotal().subtract(counterAmount)).build());
break;
default :
throw new NotAvailableFromExchangeException("Order type " + userTrade.getType() + " not supported");
}
}
