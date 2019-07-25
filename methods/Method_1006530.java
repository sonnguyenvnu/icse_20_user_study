@Override public IRouter with(Bundle bundle){
  if (bundle != null && !bundle.isEmpty()) {
    Bundle extras=mRouteRequest.getExtras();
    if (extras == null) {
      extras=new Bundle();
    }
    extras.putAll(bundle);
    mRouteRequest.setExtras(extras);
  }
  return this;
}
