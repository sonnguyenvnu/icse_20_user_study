private void concludeHostParams(ExchangeSpecification exchangeSpecification){
  if (exchangeSpecification.getExchangeSpecificParameters() != null) {
    if (Boolean.TRUE.equals(exchangeSpecification.getExchangeSpecificParametersItem(PARAM_SANDBOX))) {
      logger.debug("Connecting to sandbox");
      exchangeSpecification.setSslUri(KucoinExchange.SANDBOX_URI);
      exchangeSpecification.setHost(KucoinExchange.SANDBOX_HOST);
    }
 else {
      logger.debug("Connecting to live");
    }
  }
}
