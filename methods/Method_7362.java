protected int getStatsNetworkType(){
  int netType=StatsController.TYPE_WIFI;
  if (lastNetInfo != null) {
    if (lastNetInfo.getType() == ConnectivityManager.TYPE_MOBILE)     netType=lastNetInfo.isRoaming() ? StatsController.TYPE_ROAMING : StatsController.TYPE_MOBILE;
  }
  return netType;
}
