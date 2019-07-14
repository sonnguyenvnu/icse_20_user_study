protected void putRequestMetadataToHeader(Object requestObject,Map<String,String> header){
  if (requestObject instanceof RequestBase) {
    RequestBase requestBase=(RequestBase)requestObject;
    header.put(RemotingConstants.HEAD_METHOD_NAME,requestBase.getMethodName());
    header.put(RemotingConstants.HEAD_TARGET_SERVICE,requestBase.getTargetServiceUniqueName());
    if (requestBase instanceof SofaRequest) {
      SofaRequest sofaRequest=(SofaRequest)requestBase;
      header.put(RemotingConstants.HEAD_TARGET_APP,sofaRequest.getTargetAppName());
      Map<String,Object> requestProps=sofaRequest.getRequestProps();
      if (requestProps != null) {
        CodecUtils.flatCopyTo("",requestProps,header);
      }
    }
  }
}
