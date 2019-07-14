/** 
 * Ensures that the host MMSC is reachable
 * @param context is the context of the activity or service
 * @param url     is the MMSC to check
 * @param proxy   is the proxy of the APN to check
 * @throws java.io.IOException when route cannot be established
 */
public static void ensureRouteToHost(Context context,String url,String proxy) throws IOException {
  ConnectivityManager connMgr=(ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
  InetAddress inetAddr;
  if (proxy != null && proxy.trim().length() != 0) {
    try {
      inetAddr=InetAddress.getByName(proxy);
    }
 catch (    UnknownHostException e) {
      throw new IOException("Cannot establish route for " + url + ": Unknown proxy " + proxy);
    }
    try {
      Method requestRoute=ConnectivityManager.class.getMethod("requestRouteToHostAddress",Integer.TYPE,InetAddress.class);
      if (!((Boolean)requestRoute.invoke(connMgr,ConnectivityManager.TYPE_MOBILE_MMS,inetAddr))) {
        throw new IOException("Cannot establish route to proxy " + inetAddr);
      }
    }
 catch (    Exception e) {
      Timber.e(e,"Cannot establishh route to proxy " + inetAddr);
    }
  }
 else {
    Uri uri=Uri.parse(url);
    try {
      inetAddr=InetAddress.getByName(uri.getHost());
    }
 catch (    UnknownHostException e) {
      throw new IOException("Cannot establish route for " + url + ": Unknown host");
    }
    try {
      Method requestRoute=ConnectivityManager.class.getMethod("requestRouteToHostAddress",Integer.TYPE,InetAddress.class);
      if (!((Boolean)requestRoute.invoke(connMgr,ConnectivityManager.TYPE_MOBILE_MMS,inetAddr))) {
        throw new IOException("Cannot establish route to proxy " + inetAddr);
      }
    }
 catch (    Exception e) {
      Timber.e(e,"Cannot establishh route to proxy " + inetAddr + " for " + url);
    }
  }
}
