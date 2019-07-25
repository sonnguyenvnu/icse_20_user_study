protected long increase(long lastUsage,long usage,long lastTime,long now,long windowSize){
  long averageLastUsage=divideCeil(lastUsage * precision,windowSize);
  long averageUsage=divideCeil(usage * precision,windowSize);
  if (lastTime != now) {
    assert now > lastTime;
    if (lastTime + windowSize > now) {
      long delta=now - lastTime;
      double decay=(windowSize - delta) / (double)windowSize;
      averageLastUsage=Math.round(averageLastUsage * decay);
    }
 else {
      averageLastUsage=0;
    }
  }
  averageLastUsage+=averageUsage;
  return getUsage(averageLastUsage,windowSize);
}
