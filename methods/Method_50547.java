/** 
 * Hmily load balancer rule.
 * @return the rule
 */
@Bean @ConditionalOnProperty(name="hmily.ribbon.rule.enabled") @Scope("prototype") public IRule hmilyLoadBalancer(){
  return new HmilyZoneAwareLoadBalancer();
}
