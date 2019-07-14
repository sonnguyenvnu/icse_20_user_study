@Override public synchronized void onTransferStart(DataSource source,DataSpec dataSpec,boolean isNetwork){
  if (!isNetwork) {
    return;
  }
  if (streamCount == 0) {
    sampleStartTimeMs=clock.elapsedRealtime();
  }
  streamCount++;
}
