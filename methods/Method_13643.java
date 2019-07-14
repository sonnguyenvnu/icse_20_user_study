@Bean @ConditionalOnBean(AutoServiceRegistrationProperties.class) @ConditionalOnProperty(value="spring.cloud.service-registry.auto-registration.enabled",matchIfMissing=true) public AnsRegistration ansRegistration(AnsProperties ansProperties,ApplicationContext applicationContext){
  return new AnsRegistration(ansProperties,applicationContext);
}
