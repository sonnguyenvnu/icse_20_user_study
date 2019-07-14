public static List<Balance> adaptBitcointoyouBalances(BitcointoyouBalance bitcointoyouBalances){
  List<Balance> balances=new ArrayList<>();
  if (bitcointoyouBalances != null && bitcointoyouBalances.getoReturn() != null && bitcointoyouBalances.getoReturn().size() > 0) {
    Map<String,BigDecimal> balancesMap=bitcointoyouBalances.getoReturn().get(0);
    for (    Map.Entry<String,BigDecimal> balance : balancesMap.entrySet()) {
      Currency currency=Currency.getInstance(balance.getKey());
      balances.add(new Balance(currency,balance.getValue()));
    }
  }
  return balances;
}
