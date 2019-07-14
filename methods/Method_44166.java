/** 
 * Create a exchange using the keys provided in a bitcoinde/exchangeConfiguration.json file on the classpath.
 * @return Create exchange or null if .json file was not on classpath.
 */
public static Exchange createExchangeFromJsonConfiguration() throws IOException {
  ExchangeSpecification exSpec=new ExchangeSpecification(BitcoindeExchange.class);
  ObjectMapper mapper=new ObjectMapper();
  InputStream is=ExchangeUtils.class.getClassLoader().getResourceAsStream("bitcoinde/exchangeConfiguration.json");
  if (is == null) {
    return null;
  }
  try {
    ExchangeConfiguration conf=mapper.readValue(is,ExchangeConfiguration.class);
    if (conf.apiKey != null)     exSpec.setApiKey(conf.apiKey);
    if (conf.secretKey != null)     exSpec.setSecretKey(conf.secretKey);
  }
 catch (  Exception e) {
    return null;
  }
  Exchange exchange=ExchangeFactory.INSTANCE.createExchange(exSpec);
  exchange.remoteInit();
  return exchange;
}
