public static List<UserTrade> adaptUserTransactionsToUserTrades(Bl3pUserTransactions.Bl3pUserTransaction[] transactions){
  List<UserTrade> result=new ArrayList<>(transactions.length);
  for (  Bl3pUserTransactions.Bl3pUserTransaction t : transactions) {
    UserTrade ut=new UserTrade.Builder().currencyPair(CurrencyPair.BTC_EUR).id(Integer.toString(t.id)).orderId(Integer.toString(t.orderId)).type(t.type == "credit" ? Order.OrderType.BID : Order.OrderType.ASK).timestamp(t.date).price(t.price.value).feeAmount(t.fee.value).feeCurrency(Currency.getInstance(t.fee.currency)).originalAmount(t.amount.value).build();
    result.add(ut);
  }
  return result;
}
