@Override public String requestDepositAddress(Currency currency,String... args) throws IOException {
  CoinbaseProAccount[] coinbaseAccounts=getCoinbaseAccounts();
  CoinbaseProAccount depositAccount=null;
  for (  CoinbaseProAccount account : coinbaseAccounts) {
    Currency accountCurrency=Currency.getInstance(account.getCurrency());
    if (account.isActive() && account.getType().equals("wallet") && accountCurrency.equals(currency)) {
      depositAccount=account;
      break;
    }
  }
  CoinbaseProAccountAddress depositAddress=getCoinbaseAccountAddress(depositAccount.getId());
  return depositAddress.getAddress();
}
