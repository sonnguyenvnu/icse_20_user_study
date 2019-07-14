public static AccountInfo adaptAccounts(CryptoFacilitiesAccounts cryptoFacilitiesAccounts,String username){
  Map<String,CryptoFacilitiesAccountInfo> accounts=cryptoFacilitiesAccounts.getAccounts();
  List<Wallet> wallets=new ArrayList<>();
  for (  String accountName : accounts.keySet()) {
    List<Balance> balances=new ArrayList<>(accounts.get(accountName).getBalances().size());
    Balance balance;
    for (    Entry<String,BigDecimal> balancePair : accounts.get(accountName).getBalances().entrySet()) {
      if (!accountName.equalsIgnoreCase("cash") && balancePair.getKey().equalsIgnoreCase("xbt")) {
        balance=new Balance(Currency.BTC,balancePair.getValue(),accounts.get(accountName).getAuxiliary().get("af"));
      }
 else {
        Currency currency=adaptCurrency(balancePair.getKey());
        balance=new Balance(currency,balancePair.getValue());
      }
      balances.add(balance);
    }
    wallets.add(new Wallet(accountName,accountName,balances));
  }
  return new AccountInfo(username,wallets);
}
