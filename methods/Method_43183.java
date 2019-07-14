public static AccountInfo adaptAccountInfo(BithumbAccount account,BithumbBalance balance){
  List<Balance> balances=new ArrayList<>();
  for (  String currency : balance.getCurrencies()) {
    final Balance xchangeBalance=new Balance(Currency.getInstance(currency),balance.getTotal(currency),balance.getAvailable(currency),balance.getFrozen(currency));
    balances.add(xchangeBalance);
  }
  return new AccountInfo(null,account.getTradeFee(),new Wallet(balances));
}
