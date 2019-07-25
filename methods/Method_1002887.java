/** 
 * Configures SSL or TLS of this  {@link VirtualHost} with the specified {@code keyCertChainFile}, cleartext  {@code keyFile} and {@code tlsCustomizer}.
 */
public VirtualHostBuilder tls(File keyCertChainFile,File keyFile,Consumer<SslContextBuilder> tlsCustomizer) throws SSLException {
  tls(keyCertChainFile,keyFile,null,tlsCustomizer);
  return this;
}
