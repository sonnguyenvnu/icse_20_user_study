/** 
 * @see com.alipay.remoting.rpc.RpcRemoting#oneway(com.alipay.remoting.Url,java.lang.Object,InvokeContext)
 */
@Override public void oneway(Url url,Object request,InvokeContext invokeContext) throws RemotingException {
  Connection conn=this.connectionManager.get(url.getUniqueKey());
  if (null == conn) {
    throw new RemotingException("Client address [" + url.getOriginUrl() + "] not connected yet!");
  }
  this.connectionManager.check(conn);
  this.oneway(conn,request,invokeContext);
}
