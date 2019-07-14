public static List<Wallet> adaptWallets(BitfinexBalancesResponse[] response){
  Map<String,Map<String,BigDecimal[]>> walletsBalancesMap=new HashMap<>();
  for (  BitfinexBalancesResponse balance : response) {
    String walletId=balance.getType();
    if (!walletsBalancesMap.containsKey(walletId)) {
      walletsBalancesMap.put(walletId,new HashMap<>());
    }
    Map<String,BigDecimal[]> balancesByCurrency=walletsBalancesMap.get(walletId);
    String currencyName=adaptBitfinexCurrency(balance.getCurrency());
    BigDecimal[] balanceDetail=balancesByCurrency.get(currencyName);
    if (balanceDetail == null) {
      balanceDetail=new BigDecimal[]{balance.getAmount(),balance.getAvailable()};
    }
 else {
      balanceDetail[0]=balanceDetail[0].add(balance.getAmount());
      balanceDetail[1]=balanceDetail[1].add(balance.getAvailable());
    }
    balancesByCurrency.put(currencyName,balanceDetail);
  }
  List<Wallet> wallets=new ArrayList<>();
  for (  Entry<String,Map<String,BigDecimal[]>> walletData : walletsBalancesMap.entrySet()) {
    Map<String,BigDecimal[]> balancesByCurrency=walletData.getValue();
    List<Balance> balances=new ArrayList<>(balancesByCurrency.size());
    for (    Entry<String,BigDecimal[]> entry : balancesByCurrency.entrySet()) {
      String currencyName=entry.getKey();
      BigDecimal[] balanceDetail=entry.getValue();
      BigDecimal balanceTotal=balanceDetail[0];
      BigDecimal balanceAvailable=balanceDetail[1];
      balances.add(new Balance(Currency.getInstance(currencyName),balanceTotal,balanceAvailable));
    }
    wallets.add(new Wallet(walletData.getKey(),balances));
  }
  return wallets;
}
