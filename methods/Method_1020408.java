@Override public AgentBuilder instrument(AgentBuilder agentBuilder,Settings settings){
  ContextTrampoline.setContextStrategy(new ContextStrategyImpl());
  return agentBuilder;
}
