@Override public String changeOrder(LimitOrder limitOrder) throws ExchangeException {
  BitmexPrivateOrder order=replaceOrder(new BitmexReplaceOrderParameters.Builder().setOrderId(limitOrder.getId()).setOrderQuantity(limitOrder.getOriginalAmount()).setPrice(limitOrder.getLimitPrice()).build());
  return order.getId();
}
