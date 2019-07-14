@Bean @ConditionalOnMissingBean public ApplicationsController applicationsController(InstanceRegistry instanceRegistry,InstanceEventPublisher eventPublisher){
  return new ApplicationsController(instanceRegistry,eventPublisher);
}
