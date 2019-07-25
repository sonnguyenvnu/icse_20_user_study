/** 
 * Attempts to parse an endpoint spec into an InetSocketAddress.
 * @param value the endpoint spec
 * @return a parsed InetSocketAddress
 * @throws NullPointerException     if {@code value} is {@code null}
 * @throws IllegalArgumentException if {@code value} cannot be parsed
 */
public static InetSocketAddress parse(String value){
  Preconditions.checkNotNull(value);
  String[] spec=value.split(":",2);
  if (spec.length != 2) {
    throw new IllegalArgumentException("Invalid socket address spec: " + value);
  }
  String host=spec[0];
  int port=asPort(spec[1]);
  return StringUtils.isEmpty(host) ? new InetSocketAddress(port) : InetSocketAddress.createUnresolved(host,port);
}
