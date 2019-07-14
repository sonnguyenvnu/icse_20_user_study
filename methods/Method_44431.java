public static KrakenOrderBuilder getStopLossAndLimitOrderBuilder(CurrencyPair currencyPair,KrakenType type,String stopLossPrice,String limitPrice,BigDecimal volume){
  return new KrakenOrderBuilder(currencyPair,type,KrakenOrderType.STOP_LOSS_AND_LIMIT,volume).withPrice(stopLossPrice).withSecondaryPrice(limitPrice);
}
