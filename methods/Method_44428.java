public static KrakenOrderBuilder getStopLossOrderBuilder(CurrencyPair currencyPair,KrakenType type,String stopLossPrice,BigDecimal volume){
  return new KrakenOrderBuilder(currencyPair,type,KrakenOrderType.STOP_LOSS,volume).withPrice(stopLossPrice);
}
