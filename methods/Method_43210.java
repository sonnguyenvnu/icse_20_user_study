private static UserTrade createHistoryTrade(BitMarketHistoryTrade trade,BitMarketHistoryOperations operations){
  String commissionCurrency=BitMarketUtils.bitMarketOrderTypeToOrderType(trade.getType()) == OrderType.BID ? trade.getCurrencyCrypto() : trade.getCurrencyFiat();
  BitMarketHistoryOperation tradeOperation=null;
  for (  BitMarketHistoryOperation operation : operations.getOperations()) {
    if (operation.getType().equals("trade") && operation.getCurrency().equals(commissionCurrency) && operation.getTime() == trade.getTime()) {
      tradeOperation=operation;
      break;
    }
  }
  return new UserTrade(BitMarketUtils.bitMarketOrderTypeToOrderType(trade.getType()),trade.getAmountCrypto(),new CurrencyPair(trade.getCurrencyCrypto(),trade.getCurrencyFiat()),trade.getRate(),trade.getTimestamp(),String.valueOf(trade.getId()),tradeOperation != null ? String.valueOf(tradeOperation.getId()) : null,tradeOperation != null ? tradeOperation.getCommission() : null,Currency.getInstance(commissionCurrency));
}
