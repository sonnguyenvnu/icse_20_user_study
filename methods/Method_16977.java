@Benchmark @Group("read_only") @GroupThreads(8) public Boolean readOnly(ThreadState threadState){
  return cache.get(ints[threadState.index++ & MASK]);
}
