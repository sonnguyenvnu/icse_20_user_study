protected AbstractByteBuf encodeSofaResponse(SofaResponse sofaResponse,Map<String,String> context) throws SofaRpcException {
  AbstractByteBuf byteBuf;
  if (sofaResponse.isError()) {
    byteBuf=encode(sofaResponse.getErrorMsg(),context);
  }
 else {
    Object appResponse=sofaResponse.getAppResponse();
    if (appResponse instanceof Throwable) {
      byteBuf=encode(((Throwable)appResponse).getMessage(),context);
    }
 else {
      byteBuf=encode(appResponse,context);
    }
  }
  return byteBuf;
}
