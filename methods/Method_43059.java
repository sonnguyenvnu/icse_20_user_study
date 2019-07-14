public BitcoindeIdResponse bitcoindePlaceLimitOrder(LimitOrder l) throws IOException {
  try {
    String side=l.getType().equals(OrderType.ASK) ? "sell" : "buy";
    String bitcoindeCurrencyPair=l.getCurrencyPair().base.getCurrencyCode() + l.getCurrencyPair().counter.getCurrencyCode();
    return bitcoinde.createOrder(apiKey,nonceFactory,signatureCreator,l.getOriginalAmount(),l.getLimitPrice(),bitcoindeCurrencyPair,side);
  }
 catch (  BitcoindeException e) {
    throw handleError(e);
  }
}
