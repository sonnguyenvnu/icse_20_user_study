public BaseYoBitResponse trade(LimitOrder limitOrder) throws IOException {
  String market=YoBitAdapters.adaptCcyPairToUrlFormat(limitOrder.getCurrencyPair());
  String direction=limitOrder.getType().equals(Order.OrderType.BID) ? "buy" : "sell";
  BaseYoBitResponse response=service.trade(exchange.getExchangeSpecification().getApiKey(),signatureCreator,"Trade",exchange.getNonceFactory(),market,direction,limitOrder.getLimitPrice(),limitOrder.getOriginalAmount());
  if (!response.success)   throw new ExchangeException("failed to get place order");
  return response;
}
