/** 
 * ? token??????
 * @return token???????
 */
public static String getUsername(String token){
  try {
    DecodedJWT jwt=JWT.decode(token);
    return jwt.getClaim("username").asString();
  }
 catch (  JWTDecodeException e) {
    log.error("error?{}",e.getMessage());
    return null;
  }
}
