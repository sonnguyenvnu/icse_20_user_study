protected long increase(long lastUsage,long usage,long lastTime,long now){
  return increase(lastUsage,usage,lastTime,now,windowSize);
}
