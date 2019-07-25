@Override public void configure(ConfigProperties configProperties){
  String userNamePatternString=configProperties.getStringValue("userNamePattern",null);
  if (userNamePatternString != null) {
    userNamePattern=Pattern.compile(userNamePatternString);
  }
  password=configProperties.getStringValue("password",password);
  String saltString=configProperties.getStringValue("salt",null);
  if (saltString != null) {
    salt=StringUtils.convertHexToBytes(saltString);
  }
  String hashString=configProperties.getStringValue("hash",null);
  if (hashString != null) {
    hashWithSalt=SHA256.getHashWithSalt(StringUtils.convertHexToBytes(hashString),salt);
  }
}
