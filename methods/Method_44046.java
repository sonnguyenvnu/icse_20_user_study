public List<Balance> getBalances() throws IOException {
  CryptopiaBaseResponse<List<Map>> response=cryptopia.getBalance(signatureCreator,new HashMap<>());
  List<Balance> balances=new ArrayList<>();
  for (  Map datum : response.getData()) {
    Currency symbol=Currency.getInstance(datum.get("Symbol").toString());
    BigDecimal total=new BigDecimal(datum.get("Total").toString());
    BigDecimal available=new BigDecimal(datum.get("Available").toString());
    BigDecimal heldForTrades=new BigDecimal(datum.get("HeldForTrades").toString());
    BigDecimal pendingWithdraw=new BigDecimal(datum.get("PendingWithdraw").toString());
    BigDecimal unconfirmed=new BigDecimal(datum.get("Unconfirmed").toString());
    Balance balance=new Balance(symbol,total,available,heldForTrades,BigDecimal.ZERO,BigDecimal.ZERO,pendingWithdraw,unconfirmed);
    balances.add(balance);
  }
  return balances;
}
