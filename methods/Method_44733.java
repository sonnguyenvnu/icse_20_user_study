public static List<Balance> adaptPoloniexBalances(HashMap<String,PoloniexBalance> poloniexBalances){
  List<Balance> balances=new ArrayList<>();
  for (  Map.Entry<String,PoloniexBalance> item : poloniexBalances.entrySet()) {
    Currency currency=Currency.getInstance(item.getKey());
    balances.add(new Balance(currency,null,item.getValue().getAvailable(),item.getValue().getOnOrders()));
  }
  return balances;
}
