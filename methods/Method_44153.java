public static Exchange getExchange(){
  Exchange bleutrade=ExchangeFactory.INSTANCE.createExchange(BleutradeExchange.class.getName());
  ExchangeSpecification exchangeSpecification=bleutrade.getDefaultExchangeSpecification();
  exchangeSpecification.setApiKey("");
  exchangeSpecification.setSecretKey("");
  bleutrade.applySpecification(exchangeSpecification);
  return bleutrade;
}
