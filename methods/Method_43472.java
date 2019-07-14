/** 
 * {@inheritDoc} 
 */
@Override public String requestDepositAddress(Currency currency,String... args) throws IOException {
  return BTCTradeAdapters.adaptDepositAddress(getBTCTradeWallet());
}
