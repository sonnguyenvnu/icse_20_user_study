private <T>T service(KucoinExchange exchange,Class<T> clazz){
  return RestProxyFactory.createProxy(clazz,exchange.getExchangeSpecification().getSslUri(),getClientConfig());
}
