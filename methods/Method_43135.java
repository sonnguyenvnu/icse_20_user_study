private BitfinexOrderStatusResponse sendLimitOrder(LimitOrder limitOrder,BitfinexOrderType bitfinexOrderType,long replaceOrderId) throws IOException {
  String pair=BitfinexUtils.toPairString(limitOrder.getCurrencyPair());
  String type=(limitOrder.getType().equals(Order.OrderType.BID) || limitOrder.getType().equals(Order.OrderType.EXIT_ASK)) ? "buy" : "sell";
  String orderType=bitfinexOrderType.toString();
  boolean isHidden;
  if (limitOrder.hasFlag(BitfinexOrderFlags.HIDDEN)) {
    isHidden=true;
  }
 else {
    isHidden=false;
  }
  boolean isPostOnly;
  if (limitOrder.hasFlag(BitfinexOrderFlags.POST_ONLY)) {
    isPostOnly=true;
  }
 else {
    isPostOnly=false;
  }
  BitfinexOrderStatusResponse response;
  if (replaceOrderId == Long.MIN_VALUE) {
    BigDecimal ocoAmount=limitOrder instanceof BitfinexLimitOrder ? ((BitfinexLimitOrder)limitOrder).getOcoStopLimit() : null;
    BitfinexNewOrderRequest request=new BitfinexNewOrderRequest(String.valueOf(exchange.getNonceFactory().createValue()),pair,limitOrder.getOriginalAmount(),limitOrder.getLimitPrice(),"bitfinex",type,orderType,isHidden,isPostOnly,ocoAmount);
    response=bitfinex.newOrder(apiKey,payloadCreator,signatureCreator,request);
  }
 else {
    boolean useRemaining=limitOrder.hasFlag(BitfinexOrderFlags.USE_REMAINING);
    BitfinexReplaceOrderRequest request=new BitfinexReplaceOrderRequest(String.valueOf(exchange.getNonceFactory().createValue()),replaceOrderId,pair,limitOrder.getOriginalAmount(),limitOrder.getLimitPrice(),"bitfinex",type,orderType,isHidden,isPostOnly,useRemaining);
    response=bitfinex.replaceOrder(apiKey,payloadCreator,signatureCreator,request);
  }
  if (limitOrder instanceof BitfinexLimitOrder) {
    BitfinexLimitOrder bitfinexOrder=(BitfinexLimitOrder)limitOrder;
    bitfinexOrder.setResponse(response);
  }
  return response;
}
