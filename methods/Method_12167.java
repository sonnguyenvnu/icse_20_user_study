public static final String getSaltT(){
  SecureRandom random=new SecureRandom();
  byte bytes[]=new byte[15];
  random.nextBytes(bytes);
  String salt=org.apache.commons.codec.binary.Base64.encodeBase64String(bytes);
  return salt;
}
