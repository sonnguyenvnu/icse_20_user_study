/** 
 * Configures SSL or TLS of the default  {@link VirtualHost} from the specified {@code keyCertChainFile}, {@code keyFile} and {@code keyPassword}.
 */
public ServerBuilder tls(File keyCertChainFile,File keyFile,@Nullable String keyPassword) throws SSLException {
  defaultVirtualHostBuilder.tls(keyCertChainFile,keyFile,keyPassword);
  return this;
}
