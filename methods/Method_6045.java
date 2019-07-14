@Override protected void onCodecInitialized(String name,long initializedTimestampMs,long initializationDurationMs){
  eventDispatcher.decoderInitialized(name,initializedTimestampMs,initializationDurationMs);
  codecNeedsSetOutputSurfaceWorkaround=codecNeedsSetOutputSurfaceWorkaround(name);
}
