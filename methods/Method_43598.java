public CexIOOrder placeCexIOLimitOrder(LimitOrder limitOrder) throws IOException {
  CexIOOrder order=cexIOAuthenticated.placeOrder(signatureCreator,limitOrder.getCurrencyPair().base.getCurrencyCode(),limitOrder.getCurrencyPair().counter.getCurrencyCode(),new CexioPlaceOrderRequest((limitOrder.getType() == BID ? CexIOOrder.Type.buy : CexIOOrder.Type.sell),limitOrder.getLimitPrice(),limitOrder.getOriginalAmount(),"limit"));
  if (order.getErrorMessage() != null) {
    throw new ExchangeException(order.getErrorMessage());
  }
  return order;
}
