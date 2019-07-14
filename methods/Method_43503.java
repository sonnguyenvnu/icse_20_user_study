public BTCTurkExchangeResult placeStopLimitOrder(BigDecimal amount,BigDecimal price,BigDecimal triggerPrice,CurrencyPair pair,BTCTurkOrderTypes orderTypes) throws IOException {
  return postExchange(amount,price,triggerPrice,pair,BTCTurkOrderMethods.STOP_LIMIT,orderTypes);
}
