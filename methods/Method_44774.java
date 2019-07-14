/** 
 * This returns the currently set deposit address. It will not generate a new address (ie. repeated calls will return the same address).
 */
@Override public String requestDepositAddress(Currency currency,String... arguments) throws IOException {
  if (currency.equals(Currency.BTC))   return getQuadrigaCxBitcoinDepositAddress().getDepositAddress();
 else   if (currency.equals(Currency.ETH))   return getQuadrigaCxEtherDepositAddress().getDepositAddress();
 else   if (currency.equals(Currency.BCH))   return getQuadrigaCxBitcoinCachDepositAddress().getDepositAddress();
 else   if (currency.equals(Currency.BTG))   return getQuadrigaCxBitcoinGoldDepositAddress().getDepositAddress();
 else   if (currency.equals(Currency.LTC))   return getQuadrigaCxLitecoinDepositAddress().getDepositAddress();
 else   throw new IllegalStateException("unsupported ccy " + currency);
}
