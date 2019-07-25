@Override public IRouter build(Uri uri){
  mRouteRequest=new RouteRequest(uri);
  Bundle bundle=new Bundle();
  bundle.putString(Router.RAW_URI,uri == null ? null : uri.toString());
  mRouteRequest.setExtras(bundle);
  return this;
}
