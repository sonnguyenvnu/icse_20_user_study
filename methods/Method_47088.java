public static String deriveSftpPathFrom(String hostname,int port,String username,String password,KeyPair selectedParsedKeyPair){
  return (selectedParsedKeyPair != null || password == null) ? String.format("ssh://%s@%s:%d",username,hostname,port) : String.format("ssh://%s:%s@%s:%d",username,password,hostname,port);
}
