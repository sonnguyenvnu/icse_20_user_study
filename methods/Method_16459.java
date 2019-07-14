@Bean @ConditionalOnMissingBean(PersonScopeDataAccessHandler.class) public PersonScopeDataAccessHandler personScopeDataAccessHandler(){
  return new PersonScopeDataAccessHandler();
}
