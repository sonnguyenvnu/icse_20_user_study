public static Wallet adaptBalances(Map<String,Bl3pAccountInfo.Bl3pAccountInfoWallet> bl3pBalances){
  List<Balance> balances=new ArrayList<>(bl3pBalances.size());
  for (  Bl3pAccountInfo.Bl3pAccountInfoWallet bl3pWallet : bl3pBalances.values()) {
    balances.add(new Balance(new Currency(bl3pWallet.getAvailable().currency),bl3pWallet.getBalance().value,bl3pWallet.getAvailable().value));
  }
  return new Wallet(balances);
}
