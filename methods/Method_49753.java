public LinkProperties makeLinkProperties(){
  LinkProperties p=new LinkProperties();
  p.addLinkAddress(makeLinkAddress());
  for (  RouteInfo route : mRoutes) {
    p.addRoute(route);
  }
  if (TextUtils.isEmpty(dns1) == false) {
    p.addDns(NetworkUtilsHelper.numericToInetAddress(dns1));
  }
 else {
    Timber.d("makeLinkProperties with empty dns1!");
  }
  if (TextUtils.isEmpty(dns2) == false) {
    p.addDns(NetworkUtilsHelper.numericToInetAddress(dns2));
  }
 else {
    Timber.d("makeLinkProperties with empty dns2!");
  }
  return p;
}
