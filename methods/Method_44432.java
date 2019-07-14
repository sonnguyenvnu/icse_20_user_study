public static KrakenOrderBuilder getSettlePositionOrderBuilder(CurrencyPair currencyPair,KrakenType type,BigDecimal volume){
  return new KrakenOrderBuilder(currencyPair,type,KrakenOrderType.SETTLE_POSITION,volume).withLeverage("2");
}
