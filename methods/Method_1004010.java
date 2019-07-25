@Override public int _update(final long startTime,final long rtt,final int inflight,final boolean didDrop){
  final double queueSize=this.queueSize.apply((int)this.estimatedLimit);
  this.lastRtt=rtt;
  final double shortRtt=(double)rtt;
  final double longRtt=this.longRtt.add(rtt).doubleValue();
  shortRttSampleListener.addSample(shortRtt);
  longRttSampleListener.addSample(longRtt);
  queueSizeSampleListener.addSample(queueSize);
  if (longRtt / shortRtt > 2) {
    this.longRtt.update(current -> current.doubleValue() * 0.95);
  }
  if (inflight < estimatedLimit / 2) {
    return (int)estimatedLimit;
  }
  final double gradient=Math.max(0.5,Math.min(1.0,tolerance * longRtt / shortRtt));
  double newLimit=estimatedLimit * gradient + queueSize;
  newLimit=estimatedLimit * (1 - smoothing) + newLimit * smoothing;
  newLimit=Math.max(minLimit,Math.min(maxLimit,newLimit));
  if ((int)estimatedLimit != newLimit) {
    LOG.debug("New limit={} shortRtt={} ms longRtt={} ms queueSize={} gradient={}",(int)newLimit,getLastRtt(TimeUnit.MICROSECONDS) / 1000.0,getRttNoLoad(TimeUnit.MICROSECONDS) / 1000.0,queueSize,gradient);
  }
  estimatedLimit=newLimit;
  return (int)estimatedLimit;
}
