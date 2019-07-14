protected SofaResponse getFallback(SofaResponse response,Throwable t){
  FallbackFactory fallbackFactory=SofaHystrixConfig.loadFallbackFactory((ConsumerConfig)invoker.getConfig());
  if (fallbackFactory == null) {
    return super.getFallback();
  }
  Object fallback=fallbackFactory.create(new FallbackContext(invoker,request,response,t));
  if (fallback == null) {
    return super.getFallback();
  }
  try {
    Object fallbackResult=request.getMethod().invoke(fallback,request.getMethodArgs());
    SofaResponse actualResponse=new SofaResponse();
    actualResponse.setAppResponse(fallbackResult);
    return actualResponse;
  }
 catch (  IllegalAccessException e) {
    throw new SofaRpcRuntimeException("Hystrix fallback method failed to execute.",e);
  }
catch (  InvocationTargetException e) {
    throw new SofaRpcRuntimeException("Hystrix fallback method failed to execute.",e.getTargetException());
  }
}
