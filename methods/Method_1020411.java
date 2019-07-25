@Override public AgentBuilder instrument(AgentBuilder agentBuilder,Settings settings){
  TraceTrampoline.setTraceStrategy(new TraceStrategyImpl());
  return agentBuilder;
}
