@Benchmark public void evict(ThreadState threadState){
  cache.put(threadState.key++,Boolean.TRUE);
}
