public static KrakenOrderBuilder getStopLossProfitLimitOrderBuilder(CurrencyPair currencyPair,KrakenType type,String stopLossPrice,String takeProfitPrice,BigDecimal volume){
  return new KrakenOrderBuilder(currencyPair,type,KrakenOrderType.STOP_LOSS_PROFIT_LIMIT,volume).withPrice(stopLossPrice).withSecondaryPrice(takeProfitPrice);
}
