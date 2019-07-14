@TargetApi(23) public static String getCurrentNetworkInterfaceName(){
  ConnectivityManager cm=(ConnectivityManager)ApplicationLoader.applicationContext.getSystemService(Context.CONNECTIVITY_SERVICE);
  Network net=cm.getActiveNetwork();
  if (net == null)   return null;
  LinkProperties props=cm.getLinkProperties(net);
  if (props == null)   return null;
  return props.getInterfaceName();
}
