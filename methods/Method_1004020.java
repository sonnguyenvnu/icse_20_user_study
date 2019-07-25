@Override protected int _update(long startTime,long rtt,int inflight,boolean didDrop){
  Preconditions.checkArgument(rtt > 0,"rtt must be >0 but got " + rtt);
  probeCount++;
  if (shouldProbe()) {
    LOG.debug("Probe MinRTT {}",TimeUnit.NANOSECONDS.toMicros(rtt) / 1000.0);
    resetProbeJitter();
    probeCount=0;
    rtt_noload=rtt;
    return (int)estimatedLimit;
  }
  if (rtt_noload == 0 || rtt < rtt_noload) {
    LOG.debug("New MinRTT {}",TimeUnit.NANOSECONDS.toMicros(rtt) / 1000.0);
    rtt_noload=rtt;
    return (int)estimatedLimit;
  }
  rttSampleListener.addSample(rtt_noload);
  return updateEstimatedLimit(rtt,inflight,didDrop);
}
