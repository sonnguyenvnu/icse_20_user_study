@Override public String changeOrder(LimitOrder order) throws IOException {
  boolean useRemaining=order.getOriginalAmount() == null || order.hasFlag(BitfinexOrderFlags.USE_REMAINING);
  BitfinexReplaceOrderRequest request=new BitfinexReplaceOrderRequest(String.valueOf(exchange.getNonceFactory().createValue()),Long.valueOf(order.getId()),BitfinexAdapters.adaptCurrencyPair(order.getCurrencyPair()),order.getOriginalAmount(),order.getLimitPrice(),"bitfinex",BitfinexAdapters.adaptOrderType(order.getType()),BitfinexAdapters.adaptOrderFlagsToType(order.getOrderFlags()).getValue(),order.hasFlag(BitfinexOrderFlags.HIDDEN),order.hasFlag(BitfinexOrderFlags.POST_ONLY),useRemaining);
  try {
    BitfinexOrderStatusResponse response=bitfinex.replaceOrder(apiKey,payloadCreator,signatureCreator,request);
    return String.valueOf(response.getId());
  }
 catch (  BitfinexException e) {
    throw BitfinexErrorAdapter.adapt(e);
  }
}
