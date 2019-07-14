public static Wallet adaptAccountInfo(CoinbaseProAccount[] coinbaseProAccounts){
  List<Balance> balances=new ArrayList<>(coinbaseProAccounts.length);
  for (int i=0; i < coinbaseProAccounts.length; i++) {
    CoinbaseProAccount coinbaseProAccount=coinbaseProAccounts[i];
    balances.add(new Balance(Currency.getInstance(coinbaseProAccount.getCurrency()),coinbaseProAccount.getBalance(),coinbaseProAccount.getAvailable(),coinbaseProAccount.getHold()));
  }
  return new Wallet(coinbaseProAccounts[0].getProfile_id(),balances);
}
