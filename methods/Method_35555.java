@Override public BasicMappingBuilder inScenario(String scenarioName){
  checkArgument(scenarioName != null,"Scenario name must not be null");
  this.scenarioName=scenarioName;
  return this;
}
