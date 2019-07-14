private CoinbaseSell sellInternal(String accountId,SellPayload payload) throws IOException {
  String path="/v2/accounts/" + accountId + "/sells";
  String apiKey=exchange.getExchangeSpecification().getApiKey();
  BigDecimal timestamp=coinbase.getTime(Coinbase.CB_VERSION_VALUE).getData().getEpoch();
  String body=new ObjectMapper().writeValueAsString(payload);
  String signature=getSignature(timestamp,HttpMethod.POST,path,body);
  showCurl(HttpMethod.POST,apiKey,timestamp,signature,path,body);
  return coinbase.sell(MediaType.APPLICATION_JSON,Coinbase.CB_VERSION_VALUE,apiKey,signature,timestamp,accountId,payload).getData();
}
