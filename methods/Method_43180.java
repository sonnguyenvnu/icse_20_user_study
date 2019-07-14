public BitflyerParentOrderAcceptance sendParentOrder(LimitOrder limitOrder) throws IOException {
  BitflyerParentOrder.BitflyerParentOrderBuilder orderBuilder=BitflyerParentOrder.getOrderBuilder().withParameter(limitOrder.getCurrencyPair(),LIMIT,limitOrder.getType(),limitOrder.getLimitPrice(),null,limitOrder.getOriginalAmount(),null);
  try {
    return bitflyer.sendParentOrder(apiKey,exchange.getNonceFactory(),signatureCreator,orderBuilder.buildOrder());
  }
 catch (  BitflyerException e) {
    throw handleError(e);
  }
}
