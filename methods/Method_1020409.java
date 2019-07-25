@Override public AgentBuilder instrument(AgentBuilder agentBuilder,Settings settings){
  checkNotNull(agentBuilder,"agentBuilder");
  checkNotNull(settings,"settings");
  if (!settings.isEnabled("context-propagation.executor")) {
    return agentBuilder;
  }
  return agentBuilder.type(createMatcher()).transform(new Transformer());
}
