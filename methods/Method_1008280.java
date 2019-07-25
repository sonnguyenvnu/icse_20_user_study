private long limit(long bytes,String label){
  long newUsed;
  long currentUsed;
  do {
    currentUsed=this.used.get();
    newUsed=currentUsed + bytes;
    long newUsedWithOverhead=(long)(newUsed * overheadConstant);
    if (logger.isTraceEnabled()) {
      logger.trace("[{}] Adding [{}][{}] to used bytes [new used: [{}], limit: {} [{}], estimate: {} [{}]]",this.name,new ByteSizeValue(bytes),label,new ByteSizeValue(newUsed),memoryBytesLimit,new ByteSizeValue(memoryBytesLimit),newUsedWithOverhead,new ByteSizeValue(newUsedWithOverhead));
    }
    if (memoryBytesLimit > 0 && newUsedWithOverhead > memoryBytesLimit) {
      logger.warn("[{}] New used memory {} [{}] for data of [{}] would be larger than configured breaker: {} [{}], breaking",this.name,newUsedWithOverhead,new ByteSizeValue(newUsedWithOverhead),label,memoryBytesLimit,new ByteSizeValue(memoryBytesLimit));
      circuitBreak(label,newUsedWithOverhead);
    }
  }
 while (!this.used.compareAndSet(currentUsed,newUsed));
  return newUsed;
}
