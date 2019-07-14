/** 
 * Adjust host parameters depending on exchange specific parameters 
 */
private static void concludeHostParams(ExchangeSpecification exchangeSpecification){
  if (exchangeSpecification.getExchangeSpecificParameters() != null) {
    if (exchangeSpecification.getExchangeSpecificParametersItem("Use_Intl").equals(true) && exchangeSpecification.getExchangeSpecificParametersItem("Use_Futures").equals(false)) {
      exchangeSpecification.setSslUri("https://www.okex.com/api");
      exchangeSpecification.setHost("www.okex.com");
    }
 else     if (exchangeSpecification.getExchangeSpecificParametersItem("Use_Intl").equals(true) && exchangeSpecification.getExchangeSpecificParametersItem("Use_Futures").equals(true)) {
      exchangeSpecification.setSslUri("https://www.okex.com/api");
      exchangeSpecification.setHost("www.okex.com");
    }
  }
}
