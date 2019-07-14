protected void showCurl(String method,String apiKey,String timestamp,String signature,String path,String json){
  String headers=String.format("-H 'CB-VERSION: 2017-11-26' -H 'CB-ACCESS-KEY: %s' -H 'CB-ACCESS-SIGN: %s' -H 'CB-ACCESS-TIMESTAMP: %s'",apiKey,signature,timestamp);
  if (method.equalsIgnoreCase("get")) {
    Coinbase.LOG.debug(String.format("curl %s https://api.coinbase.com%s",headers,path));
  }
 else   if (method.equalsIgnoreCase("POST")) {
    String payload="-d '" + json + "'";
    Coinbase.LOG.debug(String.format("curl -X %s -H 'Content-Type: %s' %s %s https://api.coinbase.com%s",method,MediaType.APPLICATION_JSON,headers,payload,path));
  }
}
