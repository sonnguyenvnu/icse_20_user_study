private static BigDecimal getBaseCurrencyAmountFromBitstampTransaction(BitstampOrderTransaction bitstampTransaction){
  CurrencyPair currencyPair=adaptCurrencyPair(bitstampTransaction);
  if (currencyPair.base.equals(Currency.LTC))   return bitstampTransaction.getLtc();
  if (currencyPair.base.equals(Currency.BTC))   return bitstampTransaction.getBtc();
  if (currencyPair.base.equals(Currency.BCH))   return bitstampTransaction.getBch();
  if (currencyPair.base.equals(Currency.ETH))   return bitstampTransaction.getEth();
  if (currencyPair.base.equals(Currency.XRP))   return bitstampTransaction.getXrp();
  throw new NotYetImplementedForExchangeException();
}
