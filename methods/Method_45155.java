private SslHandler asSslHandler(final HttpsCertificate certificate){
  SSLEngine sslEngine=certificate.createSSLEngine();
  sslEngine.setUseClientMode(false);
  return new SslHandler(sslEngine);
}
