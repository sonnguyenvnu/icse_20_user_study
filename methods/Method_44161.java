public static Exchange createExchange(){
  Exchange exchange=ExchangeFactory.INSTANCE.createExchange(CoinbaseExchange.class.getName());
  AuthUtils.setApiAndSecretKey(exchange.getExchangeSpecification(),"coinbase");
  return exchange;
}
