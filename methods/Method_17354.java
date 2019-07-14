@Benchmark public Policy trace(){
  Policy policy=makePolicy();
  for (  long event : events) {
    policy.record(event);
  }
  Blackhole.consumeCPU(missPenalty * policy.stats().missCount());
  return policy;
}
