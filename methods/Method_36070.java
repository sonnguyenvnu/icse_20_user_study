private void putStubsInScenario(List<StubMapping> stubMappings){
  StubMapping firstScenario=stubMappings.get(0);
  String scenarioName="scenario-" + Urls.urlToPathParts(URI.create(firstScenario.getRequest().getUrl()));
  int count=1;
  for (  StubMapping stub : stubMappings) {
    stub.setScenarioName(scenarioName);
    if (count == 1) {
      stub.setRequiredScenarioState(Scenario.STARTED);
    }
 else {
      stub.setRequiredScenarioState(scenarioName + "-" + count);
    }
    if (count < stubMappings.size()) {
      stub.setNewScenarioState(scenarioName + "-" + (count + 1));
    }
    count++;
  }
}
