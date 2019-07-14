private synchronized void onConnectivityAction(){
  int networkType=networkTypeOverrideSet ? networkTypeOverride : (context == null ? C.NETWORK_TYPE_UNKNOWN : Util.getNetworkType(context));
  if (this.networkType == networkType) {
    return;
  }
  this.networkType=networkType;
  if (networkType == C.NETWORK_TYPE_OFFLINE || networkType == C.NETWORK_TYPE_UNKNOWN || networkType == C.NETWORK_TYPE_OTHER) {
    return;
  }
  this.bitrateEstimate=getInitialBitrateEstimateForNetworkType(networkType);
  long nowMs=clock.elapsedRealtime();
  int sampleElapsedTimeMs=streamCount > 0 ? (int)(nowMs - sampleStartTimeMs) : 0;
  maybeNotifyBandwidthSample(sampleElapsedTimeMs,sampleBytesTransferred,bitrateEstimate);
  sampleStartTimeMs=nowMs;
  sampleBytesTransferred=0;
  totalBytesTransferred=0;
  totalElapsedTimeMs=0;
  slidingPercentile.reset();
}
