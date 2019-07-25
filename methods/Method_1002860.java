/** 
 * Configures SSL or TLS of the default  {@link VirtualHost} from the specified {@code keyCertChainFile}, {@code keyFile},  {@code keyPassword} and {@code tlsCustomizer}.
 */
public ServerBuilder tls(File keyCertChainFile,File keyFile,@Nullable String keyPassword,Consumer<SslContextBuilder> tlsCustomizer) throws SSLException {
  defaultVirtualHostBuilder.tls(keyCertChainFile,keyFile,keyPassword,tlsCustomizer);
  return this;
}
