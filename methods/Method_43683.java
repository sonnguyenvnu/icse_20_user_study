protected String getSignature(BigDecimal timestamp,HttpMethod method,String path,String body){
  String secretKey=exchange.getExchangeSpecification().getSecretKey();
  String message=timestamp + method.toString() + path + (body != null ? body : "");
  return new HmacDigest("HmacSHA256",secretKey).hexDigest(message);
}
