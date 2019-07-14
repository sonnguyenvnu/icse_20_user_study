public static Wallet adaptWallet(QuadrigaCxBalance quadrigacxBalance){
  List<Currency> currencies=quadrigacxBalance.getCurrencyList();
  List<Balance> balances=new ArrayList<>();
  for (  Currency currency : currencies) {
    balances.add(new Balance(currency,quadrigacxBalance.getCurrencyBalance(currency),quadrigacxBalance.getCurrencyAvailable(currency)));
  }
  return new Wallet(balances);
}
