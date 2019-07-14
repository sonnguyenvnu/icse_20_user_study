/** 
 * Extract futures leverage used by spec 
 */
private static int futuresLeverageOfConfig(ExchangeSpecification exchangeSpecification){
  if (exchangeSpecification.getExchangeSpecificParameters().containsKey("Futures_Leverage")) {
    return Integer.valueOf((String)exchangeSpecification.getExchangeSpecificParameters().get("Futures_Leverage"));
  }
 else {
    return 10;
  }
}
