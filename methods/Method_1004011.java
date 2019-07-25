@Override public int _update(final long startTime,final long rtt,final int inflight,final boolean didDrop){
  lastRtt=rtt;
  minWindowRttSampleListener.addSample(rtt);
  final double queueSize=this.queueSize.apply((int)this.estimatedLimit);
  queueSizeSampleListener.addSample(queueSize);
  if (probeInterval != DISABLED && resetRttCounter-- <= 0) {
    resetRttCounter=nextProbeCountdown();
    estimatedLimit=Math.max(minLimit,queueSize);
    rttNoLoadMeasurement.reset();
    lastRtt=0;
    LOG.debug("Probe MinRTT limit={}",getLimit());
    return (int)estimatedLimit;
  }
  final long rttNoLoad=rttNoLoadMeasurement.add(rtt).longValue();
  minRttSampleListener.addSample(rttNoLoad);
  final double gradient=Math.max(0.5,Math.min(1.0,rttTolerance * rttNoLoad / rtt));
  double newLimit;
  if (didDrop) {
    newLimit=estimatedLimit / 2;
  }
 else   if (inflight < estimatedLimit / 2) {
    return (int)estimatedLimit;
  }
 else {
    newLimit=estimatedLimit * gradient + queueSize;
  }
  if (newLimit < estimatedLimit) {
    newLimit=Math.max(minLimit,estimatedLimit * (1 - smoothing) + smoothing * (newLimit));
  }
  newLimit=Math.max(queueSize,Math.min(maxLimit,newLimit));
  if ((int)newLimit != (int)estimatedLimit && LOG.isDebugEnabled()) {
    LOG.debug("New limit={} minRtt={} ms winRtt={} ms queueSize={} gradient={} resetCounter={}",(int)newLimit,TimeUnit.NANOSECONDS.toMicros(rttNoLoad) / 1000.0,TimeUnit.NANOSECONDS.toMicros(rtt) / 1000.0,queueSize,gradient,resetRttCounter);
  }
  estimatedLimit=newLimit;
  return (int)estimatedLimit;
}
