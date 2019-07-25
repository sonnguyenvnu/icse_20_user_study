/** 
 * Register a callback for the specified interface where the Homematic gateway can send its events.
 */
public void init(HmInterface hmInterface,String clientId) throws IOException {
  RpcRequest<T> request=createRpcRequest("init");
  request.addArg(getRpcCallbackUrl());
  request.addArg(clientId);
  if (config.getGatewayInfo().isHomegear()) {
    request.addArg(new Integer(0x22));
  }
  sendMessage(config.getRpcPort(hmInterface),request);
}
