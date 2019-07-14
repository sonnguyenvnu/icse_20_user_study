protected String placeOrder(String type,BigDecimal price,CurrencyPair currencyPair,BigDecimal originalAmount){
  Map map=exmo.orderCreate(signatureCreator,apiKey,exchange.getNonceFactory(),ExmoAdapters.format(currencyPair),originalAmount,price,type);
  Boolean result=(Boolean)map.get("result");
  if (!result)   throw new ExchangeException("Failed to place order: " + map.get("error"));
  return map.get("order_id").toString();
}
