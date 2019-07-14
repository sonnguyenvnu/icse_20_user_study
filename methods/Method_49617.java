private boolean validateToken(Map<String,String> credentials){
  final String token=credentials.get(PROPERTY_TOKEN);
  final Map<String,String> tokenMap=parseToken(token);
  final String username=tokenMap.get(PROPERTY_USERNAME);
  final String time=tokenMap.get("time");
  final String password=findUser(username).value(PROPERTY_PASSWORD);
  final String salt=getBcryptSaltFromStoredPassword(password);
  final String expected=generateToken(username,salt,time);
  final Long timeLong=Long.parseLong(time);
  final Long currentTime=new Date().getTime();
  final String base64Token=new String(Base64.getUrlEncoder().encode(token.getBytes()));
  if (timeLong + timeout < currentTime || expected.length() != base64Token.length()) {
    return false;
  }
 else {
    boolean isValid=true;
    for (int i=0; i < expected.length(); i++) {
      if (base64Token.charAt(i) != expected.charAt(i)) {
        isValid=false;
      }
    }
    return isValid;
  }
}
