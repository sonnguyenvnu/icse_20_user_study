/** 
 * Creates a token from an encoded token string.
 * @param token the (non-null) encoded token (three Base-64 encoded strings separatedby "." characters)
 */
public static Jwt decode(String token){
  int firstPeriod=token.indexOf('.');
  int lastPeriod=token.lastIndexOf('.');
  if (firstPeriod <= 0 || lastPeriod <= firstPeriod) {
    throw new IllegalArgumentException("JWT must have 3 tokens");
  }
  CharBuffer buffer=CharBuffer.wrap(token,0,firstPeriod);
  JwtHeader header=JwtHeaderHelper.create(buffer.toString());
  buffer.limit(lastPeriod).position(firstPeriod + 1);
  byte[] claims=b64UrlDecode(buffer);
  boolean emptyCrypto=lastPeriod == token.length() - 1;
  byte[] crypto;
  if (emptyCrypto) {
    if (!"none".equals(header.parameters.alg)) {
      throw new IllegalArgumentException("Signed or encrypted token must have non-empty crypto segment");
    }
    crypto=new byte[0];
  }
 else {
    buffer.limit(token.length()).position(lastPeriod + 1);
    crypto=b64UrlDecode(buffer);
  }
  return new JwtImpl(header,claims,crypto);
}
