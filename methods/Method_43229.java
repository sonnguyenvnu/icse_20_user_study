/** 
 * Adjust host parameters depending on exchange specific parameters 
 */
private static void concludeHostParams(ExchangeSpecification exchangeSpecification){
  if (exchangeSpecification.getExchangeSpecificParameters() != null) {
    if (exchangeSpecification.getExchangeSpecificParametersItem("Use_Sandbox").equals(true)) {
      exchangeSpecification.setSslUri("https://testnet.bitmex.com/");
      exchangeSpecification.setHost("testnet.bitmex.com");
    }
  }
}
