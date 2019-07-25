private void authenticate(GreetingPacket greetingPacket) throws IOException {
  int collation=greetingPacket.getServerCollation();
  int packetNumber=1;
  boolean usingSSLSocket=false;
  if (sslMode != SSLMode.DISABLED) {
    boolean serverSupportsSSL=(greetingPacket.getServerCapabilities() & ClientCapabilities.SSL) != 0;
    if (!serverSupportsSSL && (sslMode == SSLMode.REQUIRED || sslMode == SSLMode.VERIFY_CA || sslMode == SSLMode.VERIFY_IDENTITY)) {
      throw new IOException("MySQL server does not support SSL");
    }
    if (serverSupportsSSL) {
      SSLRequestCommand sslRequestCommand=new SSLRequestCommand();
      sslRequestCommand.setCollation(collation);
      channel.write(sslRequestCommand,packetNumber++);
      SSLSocketFactory sslSocketFactory=this.sslSocketFactory != null ? this.sslSocketFactory : sslMode == SSLMode.REQUIRED || sslMode == SSLMode.PREFERRED ? DEFAULT_REQUIRED_SSL_MODE_SOCKET_FACTORY : DEFAULT_VERIFY_CA_SSL_MODE_SOCKET_FACTORY;
      channel.upgradeToSSL(sslSocketFactory,sslMode == SSLMode.VERIFY_IDENTITY ? new TLSHostnameVerifier() : null);
      usingSSLSocket=true;
    }
  }
  AuthenticateCommand authenticateCommand=new AuthenticateCommand(schema,username,password,greetingPacket.getScramble());
  authenticateCommand.setCollation(collation);
  channel.write(authenticateCommand,packetNumber);
  byte[] authenticationResult=channel.read();
  if (authenticationResult[0] != (byte)0x00) {
    if (authenticationResult[0] == (byte)0xFF) {
      byte[] bytes=Arrays.copyOfRange(authenticationResult,1,authenticationResult.length);
      ErrorPacket errorPacket=new ErrorPacket(bytes);
      throw new AuthenticationException(errorPacket.getErrorMessage(),errorPacket.getErrorCode(),errorPacket.getSqlState());
    }
 else     if (authenticationResult[0] == (byte)0xFE) {
      switchAuthentication(authenticationResult,usingSSLSocket);
    }
 else {
      throw new AuthenticationException("Unexpected authentication result (" + authenticationResult[0] + ")");
    }
  }
}
