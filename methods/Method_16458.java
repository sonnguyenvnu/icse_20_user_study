@Bean @ConditionalOnMissingBean(OrgScopeDataAccessHandler.class) public OrgScopeDataAccessHandler orgScopeDataAccessHandler(){
  return new OrgScopeDataAccessHandler();
}
