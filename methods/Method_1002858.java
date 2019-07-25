/** 
 * Configures SSL or TLS of the default  {@link VirtualHost} from the specified {@code keyCertChainFile}, cleartext  {@code keyFile} and {@code tlsCustomizer}.
 */
public ServerBuilder tls(File keyCertChainFile,File keyFile,Consumer<SslContextBuilder> tlsCustomizer) throws SSLException {
  defaultVirtualHostBuilder.tls(keyCertChainFile,keyFile,tlsCustomizer);
  return this;
}
