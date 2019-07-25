protected final JChannel init(ProtocolStackConfigurator configurator) throws Exception {
  List<ProtocolConfiguration> configs=configurator.getProtocolStack();
  configs.forEach(ProtocolConfiguration::substituteVariables);
  prot_stack=new ProtocolStack(this);
  prot_stack.setup(configs);
  return this;
}
