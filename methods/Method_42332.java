public long getDelay(TimeUnit unit){
  return unit.convert(executeTime - System.currentTimeMillis(),TimeUnit.MILLISECONDS);
}
