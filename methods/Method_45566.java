/** 
 * set local address.
 * @param host the host
 * @param port the port
 * @return context local address
 */
@Deprecated public RpcInternalContext setLocalAddress(String host,int port){
  if (host == null) {
    return this;
  }
  if (port < 0 || port > 0xFFFF) {
    port=0;
  }
  this.localAddress=InetSocketAddress.createUnresolved(host,port);
  return this;
}
