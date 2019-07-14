@Benchmark @Group("readwrite") @GroupThreads(2) public void readwrite_put(ThreadState threadState){
  cache.put(ints[threadState.index++ & MASK],Boolean.TRUE);
}
