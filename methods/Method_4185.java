private void maybeUpdateLatency(long systemTimeUs){
  if (isOutputPcm && getLatencyMethod != null && systemTimeUs - lastLatencySampleTimeUs >= MIN_LATENCY_SAMPLE_INTERVAL_US) {
    try {
      latencyUs=castNonNull((Integer)getLatencyMethod.invoke(Assertions.checkNotNull(audioTrack))) * 1000L - bufferSizeUs;
      latencyUs=Math.max(latencyUs,0);
      if (latencyUs > MAX_LATENCY_US) {
        listener.onInvalidLatency(latencyUs);
        latencyUs=0;
      }
    }
 catch (    Exception e) {
      getLatencyMethod=null;
    }
    lastLatencySampleTimeUs=systemTimeUs;
  }
}
