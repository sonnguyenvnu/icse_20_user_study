private Trade mapTrade(CurrencyPair currencyPair,AcxTrade trade){
  return new Trade.Builder().currencyPair(currencyPair).id(trade.id).price(trade.price).originalAmount(trade.volume).timestamp(trade.createdAt).type(mapTradeType(trade.side)).build();
}
