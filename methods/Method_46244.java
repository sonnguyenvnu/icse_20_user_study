/** 
 * ????
 * @param request       ????
 * @param invokeContext ?????
 * @param timeoutMillis ????????
 * @throws RemotingException    ??????
 * @throws InterruptedException ????
 * @since 5.2.0
 */
protected void doOneWay(SofaRequest request,InvokeContext invokeContext,int timeoutMillis) throws RemotingException, InterruptedException {
  RPC_CLIENT.oneway(url,request,invokeContext);
}
