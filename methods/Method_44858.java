private void matchOff(BookOrder takerOrder,BookOrder makerOrder,BigDecimal tradeAmount){
  Date timestamp=new Date();
  UserTrade takerTrade=new UserTrade.Builder().currencyPair(currencyPair).id(randomUUID().toString()).originalAmount(tradeAmount).price(makerOrder.getLimitPrice()).timestamp(timestamp).type(takerOrder.getType()).orderId(takerOrder.getId()).feeAmount(takerOrder.getType() == ASK ? tradeAmount.multiply(makerOrder.getLimitPrice()).multiply(FEE_RATE) : tradeAmount.multiply(FEE_RATE)).feeCurrency(takerOrder.getType() == ASK ? currencyPair.counter : currencyPair.base).build();
  LOGGER.debug("Created taker trade: {}",takerTrade);
  accumulate(takerOrder,takerTrade);
  OrderType makerType=takerOrder.getType() == OrderType.ASK ? OrderType.BID : OrderType.ASK;
  UserTrade makerTrade=new UserTrade.Builder().currencyPair(currencyPair).id(randomUUID().toString()).originalAmount(tradeAmount).price(makerOrder.getLimitPrice()).timestamp(timestamp).type(makerType).orderId(makerOrder.getId()).feeAmount(makerType == ASK ? tradeAmount.multiply(makerOrder.getLimitPrice()).multiply(FEE_RATE) : tradeAmount.multiply(FEE_RATE)).feeCurrency(makerType == ASK ? currencyPair.counter : currencyPair.base).build();
  LOGGER.debug("Created maker trade: {}",makerOrder);
  accumulate(makerOrder,makerTrade);
  recordFill(new Fill(takerOrder.getApiKey(),takerTrade,true));
  recordFill(new Fill(makerOrder.getApiKey(),makerTrade,false));
  ticker=newTickerFromBook().last(makerOrder.getLimitPrice()).build();
}
