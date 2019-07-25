/** 
 * Configures SSL or TLS of the default  {@link VirtualHost} from the specified {@code keyCertChainFile}and cleartext  {@code keyFile}.
 */
public ServerBuilder tls(File keyCertChainFile,File keyFile) throws SSLException {
  defaultVirtualHostBuilder.tls(keyCertChainFile,keyFile);
  return this;
}
