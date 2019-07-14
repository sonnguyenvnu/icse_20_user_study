public static Exchange createExchange(){
  ExchangeSpecification exSpec=new ExchangeSpecification(ANXExchange.class);
  exSpec.setApiKey("");
  exSpec.setSecretKey("");
  return ExchangeFactory.INSTANCE.createExchange(exSpec);
}
