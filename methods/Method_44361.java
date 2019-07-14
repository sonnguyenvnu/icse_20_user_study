String placeHuobiLimitOrder(LimitOrder limitOrder) throws IOException {
  String type;
  if (limitOrder.getType() == OrderType.BID) {
    type="buy-limit";
  }
 else   if (limitOrder.getType() == OrderType.ASK) {
    type="sell-limit";
  }
 else {
    throw new ExchangeException("Unsupported order type.");
  }
  HuobiOrderResult result=huobi.placeLimitOrder(new HuobiCreateOrderRequest(getAccountId(),limitOrder.getOriginalAmount().toString(),limitOrder.getLimitPrice().toString(),HuobiUtils.createHuobiCurrencyPair(limitOrder.getCurrencyPair()),type),exchange.getExchangeSpecification().getApiKey(),HuobiDigest.HMAC_SHA_256,2,HuobiUtils.createUTCDate(exchange.getNonceFactory()),signatureCreator);
  return checkResult(result);
}
