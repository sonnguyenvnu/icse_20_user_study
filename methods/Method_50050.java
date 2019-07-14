@TargetApi(Build.VERSION_CODES.M) private static <T>T ensureRouteToMmsNetworkMarshmallow(Context context,Task<T> task) throws IOException {
  final MmsNetworkManager networkManager=new MmsNetworkManager(context.getApplicationContext(),Utils.getDefaultSubscriptionId());
  final ConnectivityManager connectivityManager=(ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
  Network network=null;
  try {
    network=networkManager.acquireNetwork();
    connectivityManager.bindProcessToNetwork(network);
    return task.run();
  }
 catch (  MmsNetworkException e) {
    throw new IOException(e);
  }
 finally {
    if (network != null) {
      connectivityManager.bindProcessToNetwork(null);
    }
    networkManager.releaseNetwork();
  }
}
