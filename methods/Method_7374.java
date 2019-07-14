protected void updateStats(){
  controller.getStats(stats);
  long wifiSentDiff=stats.bytesSentWifi - prevStats.bytesSentWifi;
  long wifiRecvdDiff=stats.bytesRecvdWifi - prevStats.bytesRecvdWifi;
  long mobileSentDiff=stats.bytesSentMobile - prevStats.bytesSentMobile;
  long mobileRecvdDiff=stats.bytesRecvdMobile - prevStats.bytesRecvdMobile;
  VoIPController.Stats tmp=stats;
  stats=prevStats;
  prevStats=tmp;
  if (wifiSentDiff > 0)   StatsController.getInstance(currentAccount).incrementSentBytesCount(StatsController.TYPE_WIFI,StatsController.TYPE_CALLS,wifiSentDiff);
  if (wifiRecvdDiff > 0)   StatsController.getInstance(currentAccount).incrementReceivedBytesCount(StatsController.TYPE_WIFI,StatsController.TYPE_CALLS,wifiRecvdDiff);
  if (mobileSentDiff > 0)   StatsController.getInstance(currentAccount).incrementSentBytesCount(lastNetInfo != null && lastNetInfo.isRoaming() ? StatsController.TYPE_ROAMING : StatsController.TYPE_MOBILE,StatsController.TYPE_CALLS,mobileSentDiff);
  if (mobileRecvdDiff > 0)   StatsController.getInstance(currentAccount).incrementReceivedBytesCount(lastNetInfo != null && lastNetInfo.isRoaming() ? StatsController.TYPE_ROAMING : StatsController.TYPE_MOBILE,StatsController.TYPE_CALLS,mobileRecvdDiff);
}
