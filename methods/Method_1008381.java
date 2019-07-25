/** 
 * Formats a network address and port for display purposes. <p> This formats the address with  {@link #format(InetAddress)}and appends the port number. IPv6 addresses will be bracketed. Any host information, if present is ignored. <p> Example output: <ul> <li>IPv4:  {@code 127.0.0.1:9300}</li> <li>IPv6:  {@code [::1]:9300}</li> </ul>
 * @param address IPv4 or IPv6 address with port
 * @return formatted string
 */
public static String format(InetSocketAddress address){
  return format(address.getAddress(),address.getPort());
}
