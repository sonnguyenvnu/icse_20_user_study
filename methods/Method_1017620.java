@Override public Exception run(){
  try {
    GSSManager manager=GSSManager.getInstance();
    GSSCredential clientCreds=null;
    Oid[] desiredMechs=new Oid[1];
    if (clientCredentials == null) {
      if (useSpnego && hasSpnegoSupport(manager)) {
        desiredMechs[0]=new Oid("1.3.6.1.5.5.2");
      }
 else {
        desiredMechs[0]=new Oid("1.2.840.113554.1.2.2");
      }
      GSSName clientName=manager.createName(user,GSSName.NT_USER_NAME);
      clientCreds=manager.createCredential(clientName,8 * 3600,desiredMechs,GSSCredential.INITIATE_ONLY);
    }
 else {
      desiredMechs[0]=new Oid("1.2.840.113554.1.2.2");
      clientCreds=clientCredentials;
    }
    GSSName serverName=manager.createName(kerberosServerName + "@" + host,GSSName.NT_HOSTBASED_SERVICE);
    GSSContext secContext=manager.createContext(serverName,desiredMechs[0],clientCreds,GSSContext.DEFAULT_LIFETIME);
    secContext.requestMutualAuth(true);
    byte[] inToken=new byte[0];
    byte[] outToken=null;
    boolean established=false;
    while (!established) {
      outToken=secContext.initSecContext(inToken,0,inToken.length);
      if (outToken != null) {
        LOGGER.log(Level.FINEST," FE=> Password(GSS Authentication Token)");
        pgStream.sendChar('p');
        pgStream.sendInteger4(4 + outToken.length);
        pgStream.send(outToken);
        pgStream.flush();
      }
      if (!secContext.isEstablished()) {
        int response=pgStream.receiveChar();
switch (response) {
case 'E':
          int elen=pgStream.receiveInteger4();
        ServerErrorMessage errorMsg=new ServerErrorMessage(pgStream.receiveErrorString(elen - 4));
      LOGGER.log(Level.FINEST," <=BE ErrorMessage({0})",errorMsg);
    return new PSQLException(errorMsg);
case 'R':
  LOGGER.log(Level.FINEST," <=BE AuthenticationGSSContinue");
int len=pgStream.receiveInteger4();
int type=pgStream.receiveInteger4();
inToken=pgStream.receive(len - 8);
break;
default :
return new PSQLException(GT.tr("Protocol error.  Session setup failed."),PSQLState.CONNECTION_UNABLE_TO_CONNECT);
}
}
 else {
established=true;
}
}
}
 catch (IOException e) {
return e;
}
catch (GSSException gsse) {
return new PSQLException(GT.tr("GSS Authentication failed"),PSQLState.CONNECTION_FAILURE,gsse);
}
return null;
}
