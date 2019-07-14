/** 
 * This returns the current deposit address. It does not generate a new one! Repeated calls will return the same.
 */
@Override public String requestDepositAddress(Currency currency,String... arguments) throws IOException {
  try {
    CoingiDepositWalletRequest request=new CoingiDepositWalletRequest().setCurrency(currency.getCurrencyCode().toUpperCase());
    return depositWallet(request).getAddress();
  }
 catch (  CoingiException e) {
    throw CoingiErrorAdapter.adapt(e);
  }
}
