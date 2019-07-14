public String generate(){
  byte[] verifierBytes=new byte[length];
  random.nextBytes(verifierBytes);
  return getAuthorizationCodeString(verifierBytes);
}
