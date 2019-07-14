public static Wallet adaptWallet(String name,List<HitbtcBalance> hitbtcBalances){
  List<Balance> balances=new ArrayList<>(hitbtcBalances.size());
  for (  HitbtcBalance balanceRaw : hitbtcBalances) {
    Currency currency=Currency.getInstance(balanceRaw.getCurrency());
    Balance balance=new Balance(currency,null,balanceRaw.getAvailable(),balanceRaw.getReserved());
    balances.add(balance);
  }
  return new Wallet(name,name,balances);
}
