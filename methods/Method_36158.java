public void onStubMappingUpdated(StubMapping oldMapping,StubMapping newMapping){
  if (oldMapping.isInScenario() && !newMapping.getScenarioName().equals(oldMapping.getScenarioName())) {
    Scenario scenarioForOldMapping=scenarioMap.get(oldMapping.getScenarioName()).withoutStubMapping(oldMapping);
    if (scenarioForOldMapping.getMappings().isEmpty()) {
      scenarioMap.remove(scenarioForOldMapping.getName());
    }
 else {
      scenarioMap.put(oldMapping.getScenarioName(),scenarioForOldMapping);
    }
  }
  if (newMapping.isInScenario()) {
    String scenarioName=newMapping.getScenarioName();
    Scenario scenario=firstNonNull(scenarioMap.get(scenarioName),Scenario.inStartedState(scenarioName)).withStubMapping(newMapping);
    scenarioMap.put(scenarioName,scenario);
  }
}
