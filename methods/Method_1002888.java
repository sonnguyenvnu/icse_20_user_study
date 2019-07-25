/** 
 * Configures SSL or TLS of this  {@link VirtualHost} with the specified {@code keyCertChainFile}, {@code keyFile},  {@code keyPassword} and {@code tlsCustomizer}.
 */
public VirtualHostBuilder tls(File keyCertChainFile,File keyFile,@Nullable String keyPassword,Consumer<SslContextBuilder> tlsCustomizer) throws SSLException {
  requireNonNull(keyCertChainFile,"keyCertChainFile");
  requireNonNull(keyFile,"keyFile");
  requireNonNull(tlsCustomizer,"tlsCustomizer");
  if (!keyCertChainFile.exists()) {
    throw new SSLException("non-existent certificate chain file: " + keyCertChainFile);
  }
  if (!keyCertChainFile.canRead()) {
    throw new SSLException("cannot read certificate chain file: " + keyCertChainFile);
  }
  if (!keyFile.exists()) {
    throw new SSLException("non-existent key file: " + keyFile);
  }
  if (!keyFile.canRead()) {
    throw new SSLException("cannot read key file: " + keyFile);
  }
  tls(buildSslContext(() -> SslContextBuilder.forServer(keyCertChainFile,keyFile,keyPassword),tlsCustomizer));
  return this;
}
