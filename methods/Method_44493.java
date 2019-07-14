public static RuntimeException classify(KucoinApiException e){
  if (e.getMessage().equalsIgnoreCase("Service unavailable")) {
    return new ExchangeUnavailableException(e.getMessage(),e);
  }
  if (e.getMessage().contains("check environment variables")) {
    return new ExchangeSecurityException(e.getMessage(),e);
  }
switch (e.getCode()) {
case "400001":
case "400003":
case "400004":
case "400006":
case "400007":
case "411100":
    return new ExchangeSecurityException(e.getMessage(),e);
case "400005":
  return new NonceException(e.getMessage(),e);
default :
return new ExchangeException(e.getMessage(),e);
}
}
