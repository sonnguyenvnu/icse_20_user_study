/** 
 * Release a callback for the specified interface.
 */
public void release(HmInterface hmInterface) throws IOException {
  RpcRequest<T> request=createRpcRequest("init");
  request.addArg(getRpcCallbackUrl());
  sendMessage(config.getRpcPort(hmInterface),request);
}
