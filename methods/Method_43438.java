public String buyLimit(LimitOrder limitOrder) throws IOException {
  try {
    String pairString=BleutradeUtils.toPairString(limitOrder.getCurrencyPair());
    BleutradePlaceOrderReturn response=bleutrade.buyLimit(apiKey,signatureCreator,exchange.getNonceFactory(),pairString,limitOrder.getOriginalAmount().toPlainString(),limitOrder.getLimitPrice().toPlainString());
    if (!response.getSuccess()) {
      throw new ExchangeException(response.getMessage());
    }
    return response.getResult().getOrderid();
  }
 catch (  BleutradeException e) {
    throw new ExchangeException(e);
  }
}
