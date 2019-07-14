@TargetApi(Build.VERSION_CODES.LOLLIPOP) private static <T>T ensureRouteToMmsNetworkLollipop(Context context,Task<T> task) throws IOException {
  final MmsNetworkManager networkManager=new MmsNetworkManager(context.getApplicationContext(),Utils.getDefaultSubscriptionId());
  Network network=null;
  try {
    network=networkManager.acquireNetwork();
    ConnectivityManager.setProcessDefaultNetwork(network);
    return task.run();
  }
 catch (  MmsNetworkException e) {
    throw new IOException(e);
  }
 finally {
    if (network != null) {
      ConnectivityManager.setProcessDefaultNetwork(null);
    }
    networkManager.releaseNetwork();
  }
}
