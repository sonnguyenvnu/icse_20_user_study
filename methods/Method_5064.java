private boolean checkNetworkRequirements(Context context){
  int networkRequirement=getRequiredNetworkType();
  if (networkRequirement == NETWORK_TYPE_NONE) {
    return true;
  }
  ConnectivityManager connectivityManager=(ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
  NetworkInfo networkInfo=connectivityManager.getActiveNetworkInfo();
  if (networkInfo == null || !networkInfo.isConnected()) {
    logd("No network info or no connection.");
    return false;
  }
  if (!checkInternetConnectivity(connectivityManager)) {
    return false;
  }
  if (networkRequirement == NETWORK_TYPE_ANY) {
    return true;
  }
  if (networkRequirement == NETWORK_TYPE_NOT_ROAMING) {
    boolean roaming=networkInfo.isRoaming();
    logd("Roaming: " + roaming);
    return !roaming;
  }
  boolean activeNetworkMetered=isActiveNetworkMetered(connectivityManager,networkInfo);
  logd("Metered network: " + activeNetworkMetered);
  if (networkRequirement == NETWORK_TYPE_UNMETERED) {
    return !activeNetworkMetered;
  }
  if (networkRequirement == NETWORK_TYPE_METERED) {
    return activeNetworkMetered;
  }
  throw new IllegalStateException();
}
