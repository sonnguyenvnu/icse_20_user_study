public CoinoneTradeResponse placeLimitOrderRaw(LimitOrder limitOrder) throws CoinoneException, IOException {
  CoinoneTradeRequest request=new CoinoneTradeRequest(apiKey,exchange.getNonceFactory().createValue(),limitOrder.getLimitPrice(),limitOrder.getOriginalAmount(),limitOrder.getCurrencyPair().base);
  if (limitOrder.getType().equals(OrderType.ASK)) {
    return coinone.limitSell(payloadCreator,signatureCreator,request);
  }
 else {
    return coinone.limitBuy(payloadCreator,signatureCreator,request);
  }
}
