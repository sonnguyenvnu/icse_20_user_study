public Set<String> getPossibleStates(){
  FluentIterable<String> requiredStates=from(stubMappings).transform(new Function<StubMapping,String>(){
    @Override public String apply(    StubMapping mapping){
      return mapping.getRequiredScenarioState();
    }
  }
);
  return from(stubMappings).transform(new Function<StubMapping,String>(){
    @Override public String apply(    StubMapping mapping){
      return mapping.getNewScenarioState();
    }
  }
).append(requiredStates).filter(Predicates.notNull()).toSet();
}
