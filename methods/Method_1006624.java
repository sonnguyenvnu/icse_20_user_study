/** 
 * @see com.alipay.remoting.rpc.RpcRemoting#oneway(com.alipay.remoting.Url,java.lang.Object,InvokeContext)
 */
@Override public void oneway(Url url,Object request,InvokeContext invokeContext) throws RemotingException, InterruptedException {
  final Connection conn=getConnectionAndInitInvokeContext(url,invokeContext);
  this.connectionManager.check(conn);
  this.oneway(conn,request,invokeContext);
}
