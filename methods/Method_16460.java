@Bean @ConditionalOnMissingBean(PositionScopeDataAccessHandler.class) public PositionScopeDataAccessHandler positionScopeDataAccessHandler(){
  return new PositionScopeDataAccessHandler();
}
