@Override public AgentBuilder instrument(AgentBuilder agentBuilder,Settings settings){
  checkNotNull(agentBuilder,"agentBuilder");
  checkNotNull(settings,"settings");
  if (!settings.isEnabled("context-propagation.thread")) {
    return agentBuilder;
  }
  return agentBuilder.type(isSubTypeOf(Thread.class)).transform(new Transformer());
}
