public boolean cancelOrder(CancelOrderParams params) throws ExchangeException {
  if (params instanceof DefaultCancelOrderParamId) {
    DefaultCancelOrderParamId paramsWithId=(DefaultCancelOrderParamId)params;
    return cancelOrder(paramsWithId.getOrderId());
  }
  if (params instanceof CancelAllOrders) {
    List<BitmexPrivateOrder> orders=cancelAllOrders();
    return !orders.isEmpty();
  }
  throw new NotYetImplementedForExchangeException(String.format("Unexpected type of parameter: %s",params));
}
