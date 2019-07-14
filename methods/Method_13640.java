@ConditionalOnMissingBean @ConditionalOnEnabledEndpoint @Bean public AcmEndpoint acmEndpoint(){
  return new AcmEndpoint(acmProperties,acmRefreshHistory,acmPropertySourceRepository);
}
