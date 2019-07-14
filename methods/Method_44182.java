public static Exchange getExchange(){
  ExchangeSpecification spec=new ExchangeSpecification(PoloniexExchange.class);
  spec.setApiKey("your-api-key-here");
  spec.setSecretKey("your-api-key-here");
  return ExchangeFactory.INSTANCE.createExchange(spec);
}
