public GeminiOrderStatusResponse placeGeminiLimitOrder(LimitOrder limitOrder,GeminiOrderType GeminiOrderType) throws IOException {
  String pair=GeminiUtils.toPairString(limitOrder.getCurrencyPair());
  String type=limitOrder.getType().equals(Order.OrderType.BID) ? "buy" : "sell";
  String orderType=GeminiOrderType.toString();
  Set<IOrderFlags> flags=limitOrder.getOrderFlags();
  Object[] options;
  if (flags.isEmpty()) {
    options=null;
  }
 else {
    ArrayList<String> list=new ArrayList<String>();
    if (flags.contains(GeminiOrderFlags.IMMEDIATE_OR_CANCEL)) {
      list.add("immediate-or-cancel");
    }
    if (flags.contains(GeminiOrderFlags.POST_ONLY)) {
      list.add("maker-or-cancel");
    }
    options=list.toArray();
  }
  GeminiNewOrderRequest request=new GeminiNewOrderRequest(String.valueOf(exchange.getNonceFactory().createValue()),pair,limitOrder.getOriginalAmount(),limitOrder.getLimitPrice(),"Gemini",type,orderType,options);
  try {
    GeminiOrderStatusResponse newOrder=gemini.newOrder(apiKey,payloadCreator,signatureCreator,request);
    return newOrder;
  }
 catch (  GeminiException e) {
    throw handleException(e);
  }
}
