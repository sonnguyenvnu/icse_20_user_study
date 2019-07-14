private static boolean checkInternetConnectivity(ConnectivityManager connectivityManager){
  if (Util.SDK_INT < 23) {
    return true;
  }
  Network activeNetwork=connectivityManager.getActiveNetwork();
  if (activeNetwork == null) {
    logd("No active network.");
    return false;
  }
  NetworkCapabilities networkCapabilities=connectivityManager.getNetworkCapabilities(activeNetwork);
  boolean validated=networkCapabilities == null || !networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_VALIDATED);
  logd("Network capability validated: " + validated);
  return !validated;
}
