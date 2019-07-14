@Override public String changeOrder(LimitOrder limitOrder) throws IOException {
  CexIOCancelReplaceOrderResponse response=cancelReplaceCexIOOrder(limitOrder.getCurrencyPair(),limitOrder.getType(),limitOrder.getId(),limitOrder.getOriginalAmount(),limitOrder.getLimitPrice());
  return response.getId();
}
