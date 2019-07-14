public CexIOOrder placeCexIOMarketOrder(MarketOrder marketOrder) throws IOException {
  CexIOOrder order=cexIOAuthenticated.placeOrder(signatureCreator,marketOrder.getCurrencyPair().base.getCurrencyCode(),marketOrder.getCurrencyPair().counter.getCurrencyCode(),new CexioPlaceOrderRequest((marketOrder.getType() == BID ? CexIOOrder.Type.buy : CexIOOrder.Type.sell),null,marketOrder.getOriginalAmount(),"market"));
  if (order.getErrorMessage() != null) {
    throw new ExchangeException(order.getErrorMessage());
  }
  return order;
}
