@Override public SaslNegotiator newSaslNegotiator(final InetAddress remoteAddress){
  return simpleAuthenticator.newSaslNegotiator(remoteAddress);
}
