public static KrakenOrderBuilder getTakeProfitLimitOrderBuilder(CurrencyPair currencyPair,KrakenType type,String takeProfitTriggerPrice,String triggeredLimitPrice,BigDecimal volume){
  return new KrakenOrderBuilder(currencyPair,type,KrakenOrderType.TAKE_PROFIT_LIMIT,volume).withPrice(takeProfitTriggerPrice).withSecondaryPrice(triggeredLimitPrice);
}
