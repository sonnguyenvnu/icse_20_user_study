public boolean mappingMatchesScenarioState(StubMapping mapping){
  String currentScenarioState=getByName(mapping.getScenarioName()).getState();
  return mapping.getRequiredScenarioState().equals(currentScenarioState);
}
