public static int getCurrentNetworkType(){
  if (isConnectedOrConnectingToWiFi()) {
    return StatsController.TYPE_WIFI;
  }
 else   if (isRoaming()) {
    return StatsController.TYPE_ROAMING;
  }
 else {
    return StatsController.TYPE_MOBILE;
  }
}
