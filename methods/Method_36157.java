public void onStubMappingAdded(StubMapping mapping){
  if (mapping.isInScenario()) {
    String scenarioName=mapping.getScenarioName();
    Scenario scenario=firstNonNull(scenarioMap.get(scenarioName),Scenario.inStartedState(scenarioName)).withStubMapping(mapping);
    scenarioMap.put(scenarioName,scenario);
  }
}
