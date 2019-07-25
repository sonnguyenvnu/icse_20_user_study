/** 
 * Oneway rpc invocation.<br> Notice! DO NOT modify the request object concurrently when this method is called.
 * @param conn
 * @param request
 * @param invokeContext 
 * @throws RemotingException
 */
public void oneway(final Connection conn,final Object request,final InvokeContext invokeContext) throws RemotingException {
  RequestCommand requestCommand=(RequestCommand)toRemotingCommand(request,conn,invokeContext,-1);
  requestCommand.setType(RpcCommandType.REQUEST_ONEWAY);
  preProcessInvokeContext(invokeContext,requestCommand,conn);
  super.oneway(conn,requestCommand);
}
