static HmacAuthorizationHeader parse(String s){
  String apiKey=s.split(":")[0];
  String hmac=new String(Base64.getDecoder().decode(s.split(":")[1]));
  return new HmacAuthorizationHeader(apiKey,hmac);
}
