@Override public synchronized void onTransferEnd(DataSource source,DataSpec dataSpec,boolean isNetwork){
  if (!isNetwork) {
    return;
  }
  Assertions.checkState(streamCount > 0);
  long nowMs=clock.elapsedRealtime();
  int sampleElapsedTimeMs=(int)(nowMs - sampleStartTimeMs);
  totalElapsedTimeMs+=sampleElapsedTimeMs;
  totalBytesTransferred+=sampleBytesTransferred;
  if (sampleElapsedTimeMs > 0) {
    float bitsPerSecond=(sampleBytesTransferred * 8000) / sampleElapsedTimeMs;
    slidingPercentile.addSample((int)Math.sqrt(sampleBytesTransferred),bitsPerSecond);
    if (totalElapsedTimeMs >= ELAPSED_MILLIS_FOR_ESTIMATE || totalBytesTransferred >= BYTES_TRANSFERRED_FOR_ESTIMATE) {
      bitrateEstimate=(long)slidingPercentile.getPercentile(0.5f);
    }
    maybeNotifyBandwidthSample(sampleElapsedTimeMs,sampleBytesTransferred,bitrateEstimate);
    sampleStartTimeMs=nowMs;
    sampleBytesTransferred=0;
  }
  streamCount--;
}
