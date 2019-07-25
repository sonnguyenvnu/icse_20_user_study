/** 
 * An implementation that infers the public address from the current request. <p> The public address is inferred based on the following: <ul> <li>X-Forwarded-Host header (if included in request)</li> <li>X-Forwarded-Proto or X-Forwarded-Ssl headers (if included in request)</li> <li>Absolute request URI (if included in request)</li> <li>Host header (if included in request)</li> <li>Protocol of request (i.e. http or https)</li> </ul> <p> This implementation is implicitly used if no  {@link ServerConfigBuilder#publicAddress(URI)} was specified.
 * @param defaultScheme the scheme ({@code http} or {@code https}) if what to use can't be determined from the request
 * @return a public address
 * @since 1.2
 */
static PublicAddress inferred(String defaultScheme){
  return new InferringPublicAddress(defaultScheme);
}
