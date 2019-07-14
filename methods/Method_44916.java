public static AccountInfo adaptVaultoroBalances(List<VaultoroBalance> vaultoroBalances){
  List<Balance> balances=new ArrayList<>();
  for (  VaultoroBalance vaultoroBalance : vaultoroBalances) {
    balances.add(adaptVaultoroBalance(vaultoroBalance));
  }
  return new AccountInfo(new Wallet(balances));
}
