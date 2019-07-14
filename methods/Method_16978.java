@Benchmark @Group("write_only") @GroupThreads(8) public void writeOnly(ThreadState threadState){
  cache.put(ints[threadState.index++ & MASK],Boolean.TRUE);
}
