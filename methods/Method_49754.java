public void updateFromDhcpRequest(DhcpInfoInternal orig){
  if (orig == null)   return;
  if (TextUtils.isEmpty(dns1)) {
    dns1=orig.dns1;
  }
  if (TextUtils.isEmpty(dns2)) {
    dns2=orig.dns2;
  }
  if (mRoutes.size() == 0) {
    for (    RouteInfo route : orig.getRoutes()) {
      addRoute(route);
    }
  }
}
