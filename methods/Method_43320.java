public BitstampDepositAddress requestDepositAddressObject(Currency currency,String... arguments) throws IOException {
  if (currency.equals(Currency.BTC)) {
    return getBitstampBitcoinDepositAddress();
  }
 else   if (currency.equals(Currency.LTC)) {
    return getBitstampLitecoinDepositAddress();
  }
 else   if (currency.equals(Currency.XRP)) {
    return getRippleDepositAddress();
  }
 else   if (currency.equals(Currency.BCH)) {
    return getBitstampBitcoinCashDepositAddress();
  }
 else   if (currency.equals(Currency.ETH)) {
    return getBitstampEthereumDepositAddress();
  }
 else {
    throw new IllegalStateException("Unsupported currency " + currency);
  }
}
