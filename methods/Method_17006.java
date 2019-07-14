@Benchmark public Integer inherit_get(ThreadState threadState){
  return inherit.get(threadState.index++ & MASK);
}
