@Override public String requestDepositAddress(Currency currency,String... arguments) throws IOException {
  CexIOCryptoAddress cryptoAddress=getCexIOCryptoAddress(currency.getCurrencyCode());
  return cryptoAddress.getData();
}
