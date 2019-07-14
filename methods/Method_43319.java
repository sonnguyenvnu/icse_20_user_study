/** 
 * This returns the currently set deposit address. It will not generate a new address (ie. repeated calls will return the same address).
 */
@Override public String requestDepositAddress(Currency currency,String... arguments) throws IOException {
  if (currency.equals(Currency.BTC)) {
    return getBitstampBitcoinDepositAddress().getDepositAddress();
  }
 else   if (currency.equals(Currency.LTC)) {
    return getBitstampLitecoinDepositAddress().getDepositAddress();
  }
 else   if (currency.equals(Currency.XRP)) {
    return getRippleDepositAddress().getAddressAndDt();
  }
 else   if (currency.equals(Currency.BCH)) {
    return getBitstampBitcoinCashDepositAddress().getDepositAddress();
  }
 else   if (currency.equals(Currency.ETH)) {
    return getBitstampEthereumDepositAddress().getDepositAddress();
  }
 else {
    throw new IllegalStateException("Unsupported currency " + currency);
  }
}
