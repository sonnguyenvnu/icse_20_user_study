public String send() throws GSSException {
  byte[] sendData;
  if (gssContext == null) {
    Assert.isTrue(token == null,"GSS Context not yet initialized. Client must be the initiator.");
    gssContext=gssManager.createContext(servicePrincipalName,spnegoOID,userCredential,GSSCredential.DEFAULT_LIFETIME);
    sendData=gssContext.initSecContext(new byte[0],0,0);
  }
 else   if (token != null) {
    sendData=gssContext.initSecContext(token,0,token.length);
    token=null;
  }
 else {
    throw new EsHadoopTransportException("Missing required negotiation token");
  }
  if (sendData == null) {
    return null;
  }
 else {
    return new String(Base64.encodeBase64(sendData),StringUtils.UTF_8);
  }
}
