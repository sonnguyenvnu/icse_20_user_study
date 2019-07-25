@NonNull @Override public RouteResponse intercept(Chain chain){
  RouteRequest request=chain.getRequest();
  if (request.getUri() == null) {
    return RouteResponse.assemble(RouteStatus.FAILED,"uri == null.");
  }
  Context context=null;
  if (chain.getSource() instanceof Context) {
    context=(Context)chain.getSource();
  }
 else   if (chain.getSource() instanceof Fragment) {
    if (Build.VERSION.SDK_INT >= 23) {
      context=((Fragment)chain.getSource()).getContext();
    }
 else {
      context=((Fragment)chain.getSource()).getActivity();
    }
  }
  if (context == null) {
    return RouteResponse.assemble(RouteStatus.FAILED,"Can't retrieve context from source.");
  }
  return chain.process();
}
