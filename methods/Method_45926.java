private void decodeSofaResponse(AbstractByteBuf data,SofaResponse sofaResponse,Map<String,String> head){
  if (head == null) {
    throw buildDeserializeError("head is null!");
  }
  String targetService=head.remove(RemotingConstants.HEAD_TARGET_SERVICE);
  if (targetService == null) {
    throw buildDeserializeError("HEAD_TARGET_SERVICE is null");
  }
  String methodName=head.remove(RemotingConstants.HEAD_METHOD_NAME);
  if (methodName == null) {
    throw buildDeserializeError("HEAD_METHOD_NAME is null");
  }
  boolean isError=false;
  if (StringUtils.TRUE.equals(head.remove(RemotingConstants.HEAD_RESPONSE_ERROR))) {
    isError=true;
  }
  if (!head.isEmpty()) {
    sofaResponse.setResponseProps(head);
  }
  if (isError) {
    String errorMessage=(String)decode(data,String.class,head);
    sofaResponse.setErrorMsg(errorMessage);
  }
 else {
    Class responseClass=protobufHelper.getResClass(targetService,methodName);
    Object pbRes=decode(data,responseClass,head);
    sofaResponse.setAppResponse(pbRes);
  }
}
