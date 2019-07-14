public BitfinexOrderStatusResponse placeBitfinexLimitOrder(LimitOrder limitOrder,BitfinexOrderType orderType) throws IOException {
  return sendLimitOrder(limitOrder,orderType,Long.MIN_VALUE);
}
