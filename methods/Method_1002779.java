public void inc(int numProducedBytes){
  final int oldValue=getAndAdd(numProducedBytes);
  if (oldValue <= highWatermark && oldValue + numProducedBytes > highWatermark) {
    if (cfg != null) {
      cfg.setAutoRead(false);
      numDeferredReads++;
      suspended=true;
    }
  }
}
