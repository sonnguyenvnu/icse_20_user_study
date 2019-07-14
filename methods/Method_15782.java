protected String[] decodeBase64EncodedCredentials(String encodedCredentials){
  String decodedCredentials=new String(Base64.getDecoder().decode(encodedCredentials));
  String[] credentials=decodedCredentials.split(":",2);
  return credentials.length != 2 ? null : (!StringUtils.isNullOrEmpty(credentials[0]) && !StringUtils.isNullOrEmpty(credentials[1]) ? credentials : null);
}
