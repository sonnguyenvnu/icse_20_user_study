public BitfinexOfferStatusResponse placeBitfinexFloatingRateLoanOrder(FloatingRateLoanOrder loanOrder,BitfinexOrderType orderType) throws IOException {
  String direction=loanOrder.getType() == OrderType.BID ? "loan" : "lend";
  BitfinexOfferStatusResponse newOrderResponse=bitfinex.newOffer(apiKey,payloadCreator,signatureCreator,new BitfinexNewOfferRequest(String.valueOf(exchange.getNonceFactory().createValue()),loanOrder.getCurrency(),loanOrder.getOriginalAmount(),new BigDecimal("0.0"),loanOrder.getDayPeriod(),direction));
  return newOrderResponse;
}
