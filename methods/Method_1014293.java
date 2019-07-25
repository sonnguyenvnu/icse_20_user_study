/** 
 * Sends a ping to the specified interface.
 */
public void ping(HmInterface hmInterface,String callerId) throws IOException {
  RpcRequest<T> request=createRpcRequest("ping");
  request.addArg(callerId);
  sendMessage(config.getRpcPort(hmInterface),request);
}
