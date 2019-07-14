@Override public String requestDepositAddress(Currency currency,String... arguments) throws IOException {
  String method=abucoinsPaymentMethodForCurrency(currency.getCurrencyCode());
  AbucoinsCryptoDeposit cryptoDeposit=abucoinsDepositMake(new AbucoinsCryptoDepositRequest(currency.getCurrencyCode(),method));
  if (cryptoDeposit.getMessage() != null)   throw new ExchangeException(cryptoDeposit.getMessage());
  return cryptoDeposit.getAddress();
}
