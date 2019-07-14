public void onStubServed(StubMapping mapping){
  if (mapping.isInScenario()) {
    final String scenarioName=mapping.getScenarioName();
    Scenario scenario=scenarioMap.get(scenarioName);
    if (mapping.modifiesScenarioState() && (mapping.getRequiredScenarioState() == null || scenario.getState().equals(mapping.getRequiredScenarioState()))) {
      Scenario newScenario=scenario.setState(mapping.getNewScenarioState());
      scenarioMap.put(scenarioName,newScenario);
    }
  }
}
