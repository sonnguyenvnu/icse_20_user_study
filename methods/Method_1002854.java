/** 
 * Adds a new  {@link ServerPort} that listens to the specified {@code localAddress} using the specifiedprotocol.
 * @deprecated Use {@link #http(InetSocketAddress)} or {@link #https(InetSocketAddress)}.
 */
@Deprecated public ServerBuilder port(InetSocketAddress localAddress,String protocol){
  return port(localAddress,SessionProtocol.of(requireNonNull(protocol,"protocol")));
}
