@Bean @ConditionalOnMissingBean(DistrictScopeDataAccessHandler.class) public DistrictScopeDataAccessHandler areaScopeDataAccessHandler(){
  return new DistrictScopeDataAccessHandler();
}
