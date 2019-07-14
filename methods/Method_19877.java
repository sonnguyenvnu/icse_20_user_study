/** 
 * ?? token
 * @param username ???
 * @param secret   ?????
 * @return token
 */
public static String sign(String username,String secret){
  try {
    username=StringUtils.lowerCase(username);
    Date date=new Date(System.currentTimeMillis() + EXPIRE_TIME);
    Algorithm algorithm=Algorithm.HMAC256(secret);
    return JWT.create().withClaim("username",username).withExpiresAt(date).sign(algorithm);
  }
 catch (  Exception e) {
    log.error("error?{}",e);
    return null;
  }
}
