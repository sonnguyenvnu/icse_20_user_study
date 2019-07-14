private void decodeSofaRequest(AbstractByteBuf data,SofaRequest sofaRequest,Map<String,String> head){
  if (head == null) {
    throw buildDeserializeError("head is null!");
  }
  String targetService=head.remove(RemotingConstants.HEAD_TARGET_SERVICE);
  if (targetService != null) {
    sofaRequest.setTargetServiceUniqueName(targetService);
    String interfaceName=ConfigUniqueNameGenerator.getInterfaceName(targetService);
    sofaRequest.setInterfaceName(interfaceName);
  }
 else {
    throw buildDeserializeError("HEAD_TARGET_SERVICE is null");
  }
  String methodName=head.remove(RemotingConstants.HEAD_METHOD_NAME);
  if (methodName != null) {
    sofaRequest.setMethodName(methodName);
  }
 else {
    throw buildDeserializeError("HEAD_METHOD_NAME is null");
  }
  String targetApp=head.remove(RemotingConstants.HEAD_TARGET_APP);
  if (targetApp != null) {
    sofaRequest.setTargetAppName(targetApp);
  }
  parseRequestHeader(RemotingConstants.RPC_TRACE_NAME,head,sofaRequest);
  if (RpcInvokeContext.isBaggageEnable()) {
    parseRequestHeader(RemotingConstants.RPC_REQUEST_BAGGAGE,head,sofaRequest);
  }
  for (  Map.Entry<String,String> entry : head.entrySet()) {
    sofaRequest.addRequestProp(entry.getKey(),entry.getValue());
  }
  Class requestClass=protostuffHelper.getReqClass(targetService,sofaRequest.getMethodName());
  Object pbReq=decode(data,requestClass,head);
  sofaRequest.setMethodArgs(new Object[]{pbReq});
  sofaRequest.setMethodArgSigs(new String[]{requestClass.getName()});
}
