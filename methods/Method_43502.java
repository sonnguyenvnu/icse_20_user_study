public BTCTurkExchangeResult placeLimitOrder(BigDecimal amount,BigDecimal price,CurrencyPair pair,BTCTurkOrderTypes orderTypes) throws IOException {
  return postExchange(amount,price,BigDecimal.ZERO,pair,BTCTurkOrderMethods.LIMIT,orderTypes);
}
