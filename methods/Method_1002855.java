/** 
 * Adds a new  {@link ServerPort} that listens to the specified {@code localAddress} using the specified{@link SessionProtocol}s. Specify multiple protocols to serve more than one protocol on the same port: <pre> {@code ServerBuilder sb = new ServerBuilder(); // Serve both HTTP and HTTPS at port 8080. sb.port(new InetSocketAddress(8080), Arrays.asList(SessionProtocol.HTTP, SessionProtocol.HTTPS)); // Enable HTTPS with PROXY protocol support at port 8443. sb.port(new InetSocketAddress(8443), Arrays.asList(SessionProtocol.PROXY, SessionProtocol.HTTPS));}</pre>
 */
public ServerBuilder port(InetSocketAddress localAddress,Iterable<SessionProtocol> protocols){
  return port(new ServerPort(localAddress,protocols));
}
