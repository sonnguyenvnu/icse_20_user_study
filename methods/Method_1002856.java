/** 
 * Sets the  {@link SslContext} of the default {@link VirtualHost}.
 */
public ServerBuilder tls(SslContext sslContext){
  defaultVirtualHostBuilder.tls(sslContext);
  return this;
}
