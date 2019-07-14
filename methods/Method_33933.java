public static Jwt encode(CharSequence content,Signer signer,Map<String,String> headers){
  JwtHeader header=JwtHeaderHelper.create(signer,headers);
  byte[] claims=utf8Encode(content);
  byte[] crypto=signer.sign(concat(b64UrlEncode(header.bytes()),PERIOD,b64UrlEncode(claims)));
  return new JwtImpl(header,claims,crypto);
}
