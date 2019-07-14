private SofaResponse buildEmptyResponse(SofaRequest request){
  SofaResponse response=new SofaResponse();
  Method method=request.getMethod();
  if (method != null) {
    response.setAppResponse(ClassUtils.getDefaultPrimitiveValue(method.getReturnType()));
  }
  return response;
}
