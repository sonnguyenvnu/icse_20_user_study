@Override public SaslNegotiator newSaslNegotiator(final InetAddress remoteAddress){
  throw new RuntimeException("HMACAuthenticator does not use SASL!");
}
