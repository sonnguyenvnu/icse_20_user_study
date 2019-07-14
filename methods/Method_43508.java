public static Wallet adaptWallet(Map<String,BxBalance> currencies){
  List<Balance> balances=new ArrayList<>();
  for (  String record : currencies.keySet()) {
    Balance balance=new Balance(adaptCurrency(record),currencies.get(record).getTotal(),currencies.get(record).getAvailable(),currencies.get(record).getOrders(),BigDecimal.ZERO,BigDecimal.ZERO,currencies.get(record).getWithdrawals(),currencies.get(record).getDeposits());
    balances.add(balance);
  }
  return new Wallet(balances);
}
