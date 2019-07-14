@Benchmark @Group("readwrite") @GroupThreads(6) public Boolean readwrite_get(ThreadState threadState){
  return cache.get(ints[threadState.index++ & MASK]);
}
