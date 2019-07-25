@Override public ConnectionFactory setup(final HttpConfiguration config){
  final SslContextFactory context=new SslContextFactory();
  keyStorePath.ifPresent(context::setKeyStorePath);
  keyStorePassword.ifPresent(context::setKeyStorePassword);
  keyManagerPassword.ifPresent(context::setKeyManagerPassword);
  trustAll.ifPresent(context::setTrustAll);
  return new SslConnectionFactory(context,nextProtocol);
}
