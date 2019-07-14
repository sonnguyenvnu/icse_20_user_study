@Bean @ConditionalOnProperty(name="tx-lcn.ribbon.loadbalancer.dtx.enabled",matchIfMissing=true) @Scope("prototype") public IRule ribbonRule(Registration registration){
  return new TxlcnZoneAvoidanceRule(registration);
}
