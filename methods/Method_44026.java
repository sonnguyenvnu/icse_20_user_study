private static BigDecimal getBaseCurrencyAmountFromCryptonitTransaction(CryptonitOrderTransaction cryptonitTransaction){
  CurrencyPair currencyPair=adaptCurrencyPair(cryptonitTransaction);
  if (currencyPair.base.equals(Currency.LTC)) {
    return cryptonitTransaction.getLtc();
  }
  if (currencyPair.base.equals(Currency.BTC)) {
    return cryptonitTransaction.getBtc();
  }
  if (currencyPair.base.equals(Currency.BCH)) {
    return cryptonitTransaction.getBch();
  }
  if (currencyPair.base.equals(Currency.ETH)) {
    return cryptonitTransaction.getEth();
  }
  if (currencyPair.base.equals(Currency.XRP)) {
    return cryptonitTransaction.getXrp();
  }
  throw new NotYetImplementedForExchangeException();
}
