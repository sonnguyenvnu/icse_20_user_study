/** 
 * Configures SSL or TLS of this  {@link VirtualHost} with the specified {@link SslContext}.
 */
public VirtualHostBuilder tls(SslContext sslContext){
  checkState(this.sslContext == null,"sslContext is already set: %s",this.sslContext);
  this.sslContext=VirtualHost.validateSslContext(requireNonNull(sslContext,"sslContext"));
  return this;
}
