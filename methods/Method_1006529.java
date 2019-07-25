@Override public IRouter build(@NonNull RouteRequest request){
  mRouteRequest=request;
  Bundle bundle=mRouteRequest.getExtras();
  if (bundle == null) {
    bundle=new Bundle();
  }
  bundle.putString(Router.RAW_URI,request.getUri().toString());
  mRouteRequest.setExtras(bundle);
  return this;
}
