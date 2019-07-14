public ExchangeSpecification getSandboxExchangeSpecification(){
  final ExchangeSpecification sandboxSpec=new ExchangeSpecification(getClass());
  sandboxSpec.setSslUri("https://sandbox-api.coinmarketcap.com");
  sandboxSpec.setHost("coinmarketcap.com");
  sandboxSpec.setExchangeName("CoinMarketCap Sandbox");
  sandboxSpec.setExchangeDescription("Cryptocurrency market cap rankings, charts, and more.");
  AuthUtils.setApiAndSecretKey(sandboxSpec,"coinmarketcap-sandbox");
  return sandboxSpec;
}
