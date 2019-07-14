public BitfinexOrderStatusResponse replaceBitfinexLimitOrder(LimitOrder limitOrder,BitfinexOrderType orderType,long replaceOrderId) throws IOException {
  if (limitOrder instanceof BitfinexLimitOrder && ((BitfinexLimitOrder)limitOrder).getOcoStopLimit() != null) {
    throw new BitfinexException("OCO orders are not yet editable");
  }
  return sendLimitOrder(limitOrder,orderType,replaceOrderId);
}
