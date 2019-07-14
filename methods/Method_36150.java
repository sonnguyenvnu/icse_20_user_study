private Predicate<StubMapping> mappingMatchingAndInCorrectScenarioStateNew(final Request request){
  return new Predicate<StubMapping>(){
    public boolean apply(    StubMapping mapping){
      return mapping.getRequest().match(request,customMatchers).isExactMatch() && (mapping.isIndependentOfScenarioState() || scenarios.mappingMatchesScenarioState(mapping));
    }
  }
;
}
