private void onUtcTimestampResolved(long elapsedRealtimeOffsetMs){
  this.elapsedRealtimeOffsetMs=elapsedRealtimeOffsetMs;
  processManifest(true);
}
