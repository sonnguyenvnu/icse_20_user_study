public static Wallet adaptWallet(IndependentReserveBalance independentReserveBalance){
  List<Balance> balances=new ArrayList<>();
  for (  IndependentReserveAccount balanceAccount : independentReserveBalance.getIndependentReserveAccounts()) {
    Currency currency=Currency.getInstance(balanceAccount.getCurrencyCode().toUpperCase());
    balances.add(new Balance(currency.getCommonlyUsedCurrency(),balanceAccount.getTotalBalance(),balanceAccount.getAvailableBalance()));
  }
  return new Wallet(balances);
}
