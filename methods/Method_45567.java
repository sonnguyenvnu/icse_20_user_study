/** 
 * set remote address.
 * @param host the host
 * @param port the port
 * @return context remote address
 */
public RpcInternalContext setRemoteAddress(String host,int port){
  if (host == null) {
    return this;
  }
  if (port < 0 || port > 0xFFFF) {
    port=0;
  }
  this.remoteAddress=InetSocketAddress.createUnresolved(host,port);
  return this;
}
