@NonNull @Override public RouteResponse intercept(Chain chain){
  RealInterceptorChain realChain=(RealInterceptorChain)chain;
  Object targetInstance=realChain.getTargetInstance();
  if (targetInstance instanceof Intent) {
    RouteRequest request=chain.getRequest();
    assembleIntent((Intent)targetInstance,request);
  }
 else   if (targetInstance instanceof Fragment) {
    RouteRequest request=chain.getRequest();
    Bundle bundle=request.getExtras();
    if (bundle != null && !bundle.isEmpty()) {
      ((Fragment)targetInstance).setArguments(bundle);
    }
  }
  RouteResponse response=RouteResponse.assemble(RouteStatus.SUCCEED,null);
  if (targetInstance != null) {
    response.setResult(targetInstance);
  }
 else {
    response.setStatus(RouteStatus.FAILED);
  }
  return response;
}
