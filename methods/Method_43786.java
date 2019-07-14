public static AccountInfo adaptAccountInfo(Collection<Currency> currencies,Collection<CoinfloorBalance> rawBalances){
  Collection<Balance> balances=new ArrayList<>();
  for (  Currency currency : currencies) {
    for (    CoinfloorBalance rawBalance : rawBalances) {
      if (rawBalance.hasCurrency(currency)) {
        Balance balance=rawBalance.getBalance(currency);
        balances.add(balance);
        break;
      }
    }
  }
  Wallet wallet=new Wallet(balances);
  return new AccountInfo(wallet);
}
