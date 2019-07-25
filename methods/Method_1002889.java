/** 
 * Configures SSL or TLS of this  {@link VirtualHost} with the specified {@code keyManagerFactory}and  {@code tlsCustomizer}.
 */
public VirtualHostBuilder tls(KeyManagerFactory keyManagerFactory,Consumer<SslContextBuilder> tlsCustomizer) throws SSLException {
  requireNonNull(keyManagerFactory,"keyManagerFactory");
  requireNonNull(tlsCustomizer,"tlsCustomizer");
  tls(buildSslContext(() -> SslContextBuilder.forServer(keyManagerFactory),tlsCustomizer));
  return this;
}
