public BTCTurkExchangeResult placeMarketOrder(BigDecimal total,CurrencyPair pair,BTCTurkOrderTypes orderTypes) throws IOException {
  return postExchange(total,BigDecimal.ZERO,BigDecimal.ZERO,pair,BTCTurkOrderMethods.MARKET,orderTypes);
}
