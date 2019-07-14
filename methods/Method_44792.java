@Override public String requestDepositAddress(Currency currency,String... args) throws IOException {
  BitcoinAccount[] quoineCryptoAccountInfo=getQuoineCryptoAccountInfo();
  for (  BitcoinAccount bitcoinAccount : quoineCryptoAccountInfo) {
    Currency ccy=Currency.getInstance(bitcoinAccount.getCurrency());
    if (ccy.equals(currency))     return bitcoinAccount.getAddress();
  }
  return null;
}
