@NonNull @Override public RouteResponse process(){
  if (interceptors.isEmpty()) {
    RouteResponse response=RouteResponse.assemble(RouteStatus.SUCCEED,null);
    if (targetInstance != null) {
      response.setResult(targetInstance);
    }
 else {
      response.setStatus(RouteStatus.FAILED);
    }
    return response;
  }
  RouteInterceptor interceptor=interceptors.remove(0);
  return interceptor.intercept(this);
}
