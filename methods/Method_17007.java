@Benchmark public Integer delegate_get(ThreadState threadState){
  return delegate.get(threadState.index++ & MASK);
}
