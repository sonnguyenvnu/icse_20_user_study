public static AccountInfo adaptAccountInfo(List<TheRockBalance> trBalances,String userName){
  ArrayList<Balance> balances=new ArrayList<>(trBalances.size());
  for (  TheRockBalance blc : trBalances) {
    Currency currency=Currency.getInstance(blc.getCurrency());
    balances.add(new Balance(currency,blc.getBalance(),blc.getTradingBalance()));
  }
  return new AccountInfo(userName,new Wallet(balances));
}
