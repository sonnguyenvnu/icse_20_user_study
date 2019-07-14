public static CoinbaseRequestMoneyRequest createMoneyRequest(String from,final CoinbaseMoney amount){
  return createMoneyRequest(from,amount.getCurrency(),amount.getAmount());
}
